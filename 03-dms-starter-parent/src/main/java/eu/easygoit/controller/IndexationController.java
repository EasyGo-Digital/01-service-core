package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.IndexationApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.exception.handler.DmsExceptionHandler;
import eu.easygoit.service.IConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * The type Indexation controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(DmsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/index")
public class IndexationController extends ControllerExceptionHandler implements IndexationApi {

    @Autowired
    private IConverterService converterService;


    //https://www.baeldung.com/apache-tika
    @Override
    public ResponseEntity<Map<String, Integer>> calcKeysOccurrences(RequestContextDto requestContext,
                                                                    String[] keys, MultipartFile file) {
        try {
            File txtFile = converterService.doConvertPdfToText(file.getInputStream());
            return ResponseFactory.ResponseOk();
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
