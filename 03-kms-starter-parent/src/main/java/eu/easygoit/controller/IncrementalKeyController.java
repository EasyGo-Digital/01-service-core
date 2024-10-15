package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.IncrementalKeyControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.NextCodeDto;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.service.IKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Incremental key controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(KmsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/key")
public class IncrementalKeyController extends ControllerExceptionHandler implements IncrementalKeyControllerApi {

    @Autowired
    private IKeyService keyService;


    @Override
    public ResponseEntity<String> generateNextCode(RequestContextDto requestContext,
                                                   String domain, String entity, String attribute) {
        log.info("Call generate next code for: {}/{}/{}", domain, entity, attribute);
        try {
            return ResponseFactory.ResponseOk(keyService.getIncrementalKey(domain, entity, attribute));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<String> subscribeNextCode(//RequestContextDto requestContext,
                                                    String domain, NextCodeDto incrementalConfig) {
        log.info("Call subscribe next code generator for: {}/{}", domain, incrementalConfig);
        try {
            keyService.subscribeIncrementalKeyGenerator(AppNextCode.builder()
                    .domain(domain)
                    .entity(incrementalConfig.getEntity())
                    .attribute(incrementalConfig.getAttribute())
                    .prefix(incrementalConfig.getPrefix())
                    .suffix(incrementalConfig.getSuffix())
                    .valueLength(incrementalConfig.getValueLength())
                    .value(incrementalConfig.getValue())
                    .increment(incrementalConfig.getIncrement())
                    .build());

            return ResponseFactory.ResponseOk();
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}