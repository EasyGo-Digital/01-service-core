package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.PEBConfigDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.mapper.PEBConfigMapper;
import eu.easygoit.model.PEBConfig;
import eu.easygoit.service.impl.PEBConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Peb config controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = KmsExceptionHandler.class, mapper = PEBConfigMapper.class, minMapper = PEBConfigMapper.class, service = PEBConfigService.class)
@RequestMapping(path = "/api/v1/private/config/peb")
public class PEBConfigController extends MappedCrudController<Long, PEBConfig, PEBConfigDto, PEBConfigDto, PEBConfigService> {

}
