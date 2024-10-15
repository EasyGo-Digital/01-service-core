package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.api.AppParameterControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.AppParameterDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.AppParameterMapper;
import eu.easygoit.model.AppParameter;
import eu.easygoit.service.impl.AppParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type App parameter controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = AppParameterMapper.class, minMapper = AppParameterMapper.class, service = AppParameterService.class)
@RequestMapping(path = "/api/v1/private/appParameter")
public class AppParameterController extends MappedCrudController<Long, AppParameter, AppParameterDto, AppParameterDto, AppParameterService>
        implements AppParameterControllerApi {

    @Override
    public ResponseEntity<String> getValueByDomainAndName(RequestContextDto requestContext,
                                                          String domain, String name, Boolean allowDefault, String defaultValue) {
        log.info("Call api getPropertyByAccount {} /{}", domain, name);
        try {
            return ResponseFactory.ResponseOk(crudService().getValueByDomainAndName(domain, name, true, defaultValue));
        } catch (Throwable e) {
            log.error("<Error>: Call api getPropertyByAccount {} /{} {}", domain, name, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<String> getTechnicalAdminEmail() {
        log.info("Call api getTechnicalAdminEmail");
        try {
            return ResponseFactory.ResponseOk(crudService().getTechnicalAdminEmail());
        } catch (Throwable e) {
            log.error("<Error>: Call api getTechnicalAdminEmail", e);
            return getBackExceptionResponse(e);
        }
    }
}
