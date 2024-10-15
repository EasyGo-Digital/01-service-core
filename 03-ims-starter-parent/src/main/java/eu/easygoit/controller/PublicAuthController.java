package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.PublicAuthControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.config.AppProperties;
import eu.easygoit.config.JwtProperties;
import eu.easygoit.dto.common.SystemInfoDto;
import eu.easygoit.dto.common.UserContextDto;
import eu.easygoit.dto.data.DomainDto;
import eu.easygoit.dto.data.ThemeDto;
import eu.easygoit.dto.request.AccountAuthTypeRequest;
import eu.easygoit.dto.request.AuthenticationRequestDto;
import eu.easygoit.dto.request.RegisterNewAccountDto;
import eu.easygoit.dto.request.RequestTrackingDto;
import eu.easygoit.dto.response.AuthResponseDto;
import eu.easygoit.dto.response.UserAccountDto;
import eu.easygoit.dto.response.UserContext;
import eu.easygoit.dto.response.UserDataResponseDto;
import eu.easygoit.enums.IEnumJwtStorage;
import eu.easygoit.enums.IEnumWebToken;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.DomainMapper;
import eu.easygoit.mapper.RegisterNewAccountMapper;
import eu.easygoit.mapper.ThemeMapper;
import eu.easygoit.model.Account;
import eu.easygoit.model.Domain;
import eu.easygoit.remote.kms.KmsPublicPasswordService;
import eu.easygoit.service.IAccountService;
import eu.easygoit.service.IAuthService;
import eu.easygoit.service.IDomainService;
import eu.easygoit.service.IThemeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Public auth controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(ImsExceptionHandler.class)
@RequestMapping(path = "/api/v1/public/user")
public class PublicAuthController extends ControllerExceptionHandler implements PublicAuthControllerApi {

    private final AppProperties appProperties;
    private final JwtProperties jwtProperties;

    /**
     * The Register new account mapper.
     */
    @Autowired
    private RegisterNewAccountMapper registerNewAccountMapper;
    @Autowired
    private KmsPublicPasswordService kmsPublicPasswordService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IThemeService themeService;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IDomainService domainService;
    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private DomainMapper domainMapper;

    /**
     * Instantiates a new Public auth controller.
     *
     * @param appProperties the app properties
     * @param jwtProperties the jwt properties
     */
    public PublicAuthController(AppProperties appProperties, JwtProperties jwtProperties) {
        this.appProperties = appProperties;
        this.jwtProperties = jwtProperties;
    }

    public ResponseEntity<AuthResponseDto> authenticate(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationRequestDto authRequestDto) {
        try {
            //Remove left & right spaces
            authRequestDto.setDomain(authRequestDto.getDomain().trim().toLowerCase());
            authRequestDto.setUserName(authRequestDto.getUserName().trim().toLowerCase());
            authRequestDto.setPassword(authRequestDto.getPassword().trim());

            AuthResponseDto authenticate = authService.authenticate(RequestTrackingDto.getFromRequest(request),
                    authRequestDto.getDomain(),
                    authRequestDto.getUserName(),
                    authRequestDto.getApplication(),
                    authRequestDto.getPassword(),
                    authRequestDto.getAuthType());

            if (jwtProperties.getJwtStorageType() == IEnumJwtStorage.Types.COOKIE) {
                response.addCookie(this.createCookie("token_type", IEnumWebToken.Types.Bearer.meaning()));
                response.addCookie(this.createCookie("access_token", authenticate.getAccessToken()));
                response.addCookie(this.createCookie("refresh_token", authenticate.getRefreshToken()));
                return ResponseFactory.ResponseOk(AuthResponseDto.builder()
                        .build());
            }

            Account account = accountService.findByDomainAndUserName(authRequestDto.getDomain(), authRequestDto.getUserName());
            Domain domain = domainService.findByName(authRequestDto.getDomain());
            ThemeDto theme = themeMapper.entityToDto(themeService.findThemeByAccountCodeAndDomainCode(account.getCode(), domain.getCode()));
            UserDataResponseDto userDataResponseDto = UserDataResponseDto.builder()
                    .id(account.getId())
                    .userName(account.getCode())
                    .firstName(account.getAccountDetails().getFirstName())
                    .lastName(account.getAccountDetails().getLastName())
                    .applications(accountService.buildAllowedTools(account, authenticate.getAccessToken()))
                    .email(account.getEmail())
                    .domainId(domain.getId())
                    .domainImagePath(domain.getImagePath())
                    .language(account.getLanguage())
                    .role(account.getFunctionRole())
                    .build();

            return ResponseFactory.ResponseOk(AuthResponseDto.builder()
                    .tokenType(IEnumWebToken.Types.Bearer)
                    .accessToken(authenticate.getAccessToken())
                    .refreshToken(authenticate.getRefreshToken())
                    .authorityToken(authenticate.getAuthorityToken())
                    .userDataResponseDto(userDataResponseDto)
                    .theme(theme)
                    .systemInfo(SystemInfoDto
                            .builder()
                            .name(appProperties.getApplicationName())
                            .version(appProperties.getApplicationVersion())
                            .build())
                    .build());
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> generateForgotPWDToken(UserContextDto userContextDto) {
        try {
            kmsPublicPasswordService.generateForgotPasswordAccessToken(userContextDto);
            return ResponseFactory.ResponseOk(true);
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // Global
        return cookie;
    }

    @Override
    public ResponseEntity<Boolean> registerNewAccount(RegisterNewAccountDto registerNewAccountDto) {
        try {
            return ResponseFactory.ResponseOk(authService.registerNewAccount(registerNewAccountMapper.dtoToEntity(registerNewAccountDto)));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<DomainDto> getDomainByName(String domain) {
        log.info("get domain by name {}", domain);
        try {
            return ResponseFactory.ResponseOk(domainMapper.entityToDto(domainService.findByName(domain)));
        } catch (Throwable e) {
            log.error("<Error>: get by name : {} ", e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<UserContext> getAuthenticationType(AccountAuthTypeRequest accountAuthTypeRequest) {
        try {
            return ResponseFactory.ResponseOk(accountService.getAuthenticationType(accountAuthTypeRequest));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<List<UserAccountDto>> getAvailableEmailAccounts(String email) {
        try {
            return ResponseFactory.ResponseOk(accountService.getAvailableEmailAccounts(email));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> switchAuthType(AccountAuthTypeRequest accountAuthTypeRequest) {
        try {
            return ResponseFactory.ResponseOk(accountService.switchAuthType(accountAuthTypeRequest));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
