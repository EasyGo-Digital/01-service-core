package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.PasswordControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.common.ResetPwdViaTokenRequestDto;
import eu.easygoit.dto.request.*;
import eu.easygoit.dto.response.AccessResponseDto;
import eu.easygoit.enums.IEnumAppToken;
import eu.easygoit.enums.IEnumAuth;
import eu.easygoit.enums.IEnumPasswordStatus;
import eu.easygoit.enums.IEnumWebToken;
import eu.easygoit.exception.AccountAuthenticationException;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.jwt.IJwtService;
import eu.easygoit.mapper.AccountMapper;
import eu.easygoit.model.TokenConfig;
import eu.easygoit.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Password controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(KmsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/password")
public class PasswordController extends ControllerExceptionHandler implements PasswordControllerApi {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IDomainService domainService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private IPasswordService passwordService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IJwtService jwtService;
    @Autowired
    private ITokenConfigService tokenConfigService;

    @Override
    public ResponseEntity<Boolean> generate(//RequestContextDto requestContext,
                                            IEnumAuth.Types type,
                                            GeneratePwdRequestDto generatePwdRequest) {
        log.info("Call generate password for domain {}", generatePwdRequest);
        try {
            String password = passwordService.generateRandomPassword(
                    generatePwdRequest.getDomain()
                    , generatePwdRequest.getDomainUrl()
                    , generatePwdRequest.getEmail()
                    , generatePwdRequest.getUserName()
                    , generatePwdRequest.getFullName()
                    , type);
            //Never return the password
            //TODO Send password to the user by email
            log.info("password genarated for {}/{} : {}", generatePwdRequest.getDomain(), generatePwdRequest.getUserName(), password);
            return ResponseFactory.ResponseOk(Boolean.TRUE);
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<String> resetPasswordViaToken(//RequestContextDto requestContext,
                                                        ResetPwdViaTokenRequestDto resetPwdViaTokenRequestDto) {
        try {
            passwordService.resetPasswordViaToken(resetPwdViaTokenRequestDto);
            return ResponseFactory.ResponseOk("password changed successfully");
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<String> changePassword(RequestContextDto requestContext,
                                                 String oldPassword,
                                                 String newPassword) {
        try {
            passwordService.changePassword(requestContext.getSenderDomain(), requestContext.getSenderUser(),
                    oldPassword, newPassword);
            return ResponseFactory.ResponseOk("password changed successfully");
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> patternCheck(//RequestContextDto requestContext,
                                                CheckPwdRequestDto checkPwdRequest) {
        log.info("Call check password for domain {}", checkPwdRequest);
        try {
            return ResponseFactory.ResponseOk(passwordService.checkForPattern(checkPwdRequest.getDomain()
                    , checkPwdRequest.getPassword()));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<AccessResponseDto> getAccess(//RequestContextDto requestContext,
                                                       AccessRequestDto accessRequest) {
        log.info("Call access for domain {}", accessRequest);
        try {
            if (!domainService.isEnabled(accessRequest.getDomain().trim().toLowerCase())) {
                throw new AccountAuthenticationException("domain disabled: " + accessRequest.getDomain());
            }

            if (IEnumAuth.Types.TOKEN == accessRequest.getAuthType()) {
                try {
                    TokenConfig tokenConfig = tokenConfigService.buildTokenConfig(accessRequest.getDomain().trim().toLowerCase(),
                            IEnumAppToken.Types.ACCESS);
                    jwtService.validateToken(accessRequest.getPassword(),
                            new StringBuilder(accessRequest.getUserName().trim().toLowerCase())
                                    .append("@")
                                    .append(accessRequest.getDomain().trim().toLowerCase())
                                    .toString(),
                            tokenConfig.getSecretKey());
                } catch (Exception e) {
                    return ResponseFactory.ResponseOk(AccessResponseDto.builder()
                            .status(IEnumPasswordStatus.Types.UNAUTHORIZED)
                            .build());
                }

                return ResponseFactory.ResponseOk(AccessResponseDto.builder()
                        .status(IEnumPasswordStatus.Types.VALID)
                        .tokenType(IEnumWebToken.Types.Bearer)
                        .accessToken(tokenService.createAccessToken(accessRequest.getDomain().trim().toLowerCase(),
                                        accessRequest.getApplication(),
                                        accessRequest.getUserName().trim().toLowerCase(),
                                        accessRequest.getIsAdmin())
                                .getToken())
                        .refreshToken(tokenService.createRefreshToken(accessRequest.getDomain().trim().toLowerCase(),
                                        accessRequest.getApplication(),
                                        accessRequest.getUserName().trim().toLowerCase())
                                .getToken())
                        .authorityToken(tokenService.createAuthorityToken(accessRequest.getDomain().trim().toLowerCase(),
                                        accessRequest.getApplication(),
                                        accessRequest.getUserName().trim().toLowerCase(),
                                        accessRequest.getAuthorities())
                                .getToken())
                        .build());
            } else {
                return ResponseFactory.ResponseOk(AccessResponseDto.builder()
                        .status(passwordService.matches(accessRequest.getDomain().trim().toLowerCase()
                                , accessRequest.getUserName().trim().toLowerCase()
                                , accessRequest.getPassword()
                                , accessRequest.getAuthType()))
                        .tokenType(IEnumWebToken.Types.Bearer)
                        .accessToken(tokenService.createAccessToken(accessRequest.getDomain().trim().toLowerCase(),
                                accessRequest.getApplication(),
                                accessRequest.getUserName().trim().toLowerCase(),
                                accessRequest.getIsAdmin()).getToken())
                        .refreshToken(tokenService.createRefreshToken(accessRequest.getDomain().trim().toLowerCase(),
                                accessRequest.getApplication(),
                                accessRequest.getUserName().trim().toLowerCase()).getToken())
                        .authorityToken(tokenService.createAuthorityToken(accessRequest.getDomain().trim().toLowerCase(),
                                accessRequest.getApplication(),
                                accessRequest.getUserName().trim().toLowerCase(),
                                accessRequest.getAuthorities()).getToken())
                        .build());
            }
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<IEnumPasswordStatus.Types> matches(//RequestContextDto requestContext,
                                                             MatchesRequestDto matchesRequest) {
        log.info("Call match password for domain {}", matchesRequest);
        try {
            return ResponseFactory.ResponseOk(passwordService.matches(matchesRequest.getDomain()
                    , matchesRequest.getUserName()
                    , matchesRequest.getPassword()
                    , matchesRequest.getAuthType()));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> isPasswordExpired(//RequestContextDto requestContext,
                                                     IsPwdExpiredRequestDto isPwdExpiredRequestDto) {
        log.info("Call isPasswordExpired {}", isPwdExpiredRequestDto);
        try {
            return ResponseFactory.ResponseOk(passwordService.isExpired(isPwdExpiredRequestDto.getDomain().trim().toLowerCase()
                    , isPwdExpiredRequestDto.getEmail().trim().toLowerCase()
                    , isPwdExpiredRequestDto.getUserName().trim().toLowerCase()
                    , isPwdExpiredRequestDto.getAuthType()));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> updateAccount(//RequestContextDto requestContext,
                                                 UpdateAccountRequestDto account) {
        log.info("Call update account " + account.toString());
        try {
            return ResponseFactory.ResponseOk(accountService.checkIfExists(accountMapper.dtoToEntity(account),
                    true));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
