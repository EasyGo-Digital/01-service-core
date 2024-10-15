package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.api.AnnexControllerApi;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.AnnexDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.AnnexMapper;
import eu.easygoit.model.Annex;
import eu.easygoit.service.IAnnexService;
import eu.easygoit.service.impl.AnnexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The type Annex controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = AnnexMapper.class, minMapper = AnnexMapper.class, service = AnnexService.class)
@RequestMapping(path = "/api/v1/private/annex")
public class AnnexController extends MappedCrudController<Long, Annex, AnnexDto, AnnexDto, AnnexService>
        implements AnnexControllerApi {

    @Autowired
    private IAnnexService annexService;

    @Override
    public ResponseEntity<List<AnnexDto>> getAnnexByTableCode(RequestContextDto requestContext,
                                                              String code) {
        try {
            List<AnnexDto> list = mapper().listEntityToDto(annexService.findAnnexByTableCode(code));
            if (!CollectionUtils.isEmpty(list)) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<List<AnnexDto>> getAnnexByTableCodeAndReference(RequestContextDto requestContext, String code, String reference) {
        try {
            List<AnnexDto> list = mapper().listEntityToDto(annexService.findAnnexByTableCodeAndRef(code, reference));
            if (!CollectionUtils.isEmpty(list)) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
