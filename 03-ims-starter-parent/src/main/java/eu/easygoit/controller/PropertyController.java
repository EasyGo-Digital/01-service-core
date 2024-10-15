package eu.easygoit.controller;


import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.PropertyControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.PropertyDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.PropertyMapper;
import eu.easygoit.service.IPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The type Property controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(ImsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/property")
public class PropertyController extends ControllerExceptionHandler implements PropertyControllerApi {

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public ResponseEntity<PropertyDto> updatePropertyAccount(RequestContextDto requestContext,
                                                             String accountCode, PropertyDto property) {
        try {
            return ResponseFactory.ResponseOk(propertyMapper.entityToDto(propertyService.updateProperty(accountCode, propertyMapper.dtoToEntity(property))));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<PropertyDto> getPropertyByAccount(RequestContextDto requestContext,
                                                            String accountCode, String guiName, String name) {
        try {
            return ResponseFactory.ResponseOk(propertyMapper.entityToDto(propertyService.getPropertyByAccount(accountCode, guiName, name)));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<List<PropertyDto>> getPropertyByAccountAndGui(RequestContextDto requestContext,
                                                                        String accountCode, String guiName) {
        try {
            return ResponseFactory.ResponseOk(propertyMapper.listEntityToDto(propertyService.getPropertyByAccountAndGui(accountCode, guiName)));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
