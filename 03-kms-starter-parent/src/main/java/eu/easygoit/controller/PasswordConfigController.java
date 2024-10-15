package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.PasswordConfigDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.mapper.PasswordConfigMapper;
import eu.easygoit.model.PasswordConfig;
import eu.easygoit.service.impl.PasswordConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Password config controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = KmsExceptionHandler.class, mapper = PasswordConfigMapper.class, minMapper = PasswordConfigMapper.class, service = PasswordConfigService.class)
@RequestMapping(path = "/api/v1/private/config/password")
public class PasswordConfigController extends MappedCrudController<Long, PasswordConfig, PasswordConfigDto, PasswordConfigDto, PasswordConfigService> {
}
