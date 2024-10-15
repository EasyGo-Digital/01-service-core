package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.PublicPasswordControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.UserContextDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Public password controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(KmsExceptionHandler.class)
@RequestMapping(path = "/api/v1/public/password")
public class PublicPasswordController extends ControllerExceptionHandler implements PublicPasswordControllerApi {

    @Autowired
    private ITokenService tokenService;

    @Override
    public ResponseEntity<Boolean> generateForgotPasswordAccessToken(UserContextDto userContextDto) {
        log.info("Call generateForgotPasswordAccessToken " + userContextDto.toString());
        try {
            tokenService.createForgotPasswordAccessToken(userContextDto.getDomain(),
                    userContextDto.getApplication(),
                    userContextDto.getUserName());
            return ResponseFactory.ResponseOk(true);
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
