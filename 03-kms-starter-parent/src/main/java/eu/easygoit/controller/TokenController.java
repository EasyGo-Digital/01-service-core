package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.common.TokenDto;
import eu.easygoit.dto.data.TokenRequestDto;
import eu.easygoit.enums.IEnumAppToken;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.service.ITokenService;
import eu.easygoit.service.TokenServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Token controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(KmsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/token")
public class TokenController extends ControllerExceptionHandler implements TokenServiceApi {

    @Autowired
    private ITokenService tokenService;

    @Override
    public ResponseEntity<TokenDto> createTokenByDomain(//RequestContextDto requestContext,
                                                        String domain,
                                                        String application,
                                                        IEnumAppToken.Types tokenType,
                                                        TokenRequestDto tokenRequestDto) {
        log.info("Call create Token By Domain");
        try {
            return ResponseFactory.ResponseOk(tokenService.buildTokenAndSave(domain, application, tokenType, tokenRequestDto.getSubject(), tokenRequestDto.getClaims()));
        } catch (Throwable e) {
            log.error("<Error>: create Token By Domain: {} ", e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> isTokenValid(RequestContextDto requestContext,
                                                String domain,
                                                String application,
                                                IEnumAppToken.Types tokenType,
                                                String token,
                                                String subject) {
        log.info("Call is Token Valid");
        try {
            return ResponseFactory.ResponseOk(tokenService.isTokenValid(domain, application, tokenType, token, subject));
        } catch (Throwable e) {
            log.error("<Error>: Invalid token: {} ", e);
            return getBackExceptionResponse(e);
        }
    }
}