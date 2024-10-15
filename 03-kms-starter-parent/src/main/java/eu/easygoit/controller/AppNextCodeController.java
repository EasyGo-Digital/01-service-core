package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.common.NextCodeDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.mapper.AppNextCodeMapper;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.service.impl.AppNextCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type App Next code controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = KmsExceptionHandler.class, mapper = AppNextCodeMapper.class, minMapper = AppNextCodeMapper.class, service = AppNextCodeService.class)
@RequestMapping(path = "/api/v1/private/code")
public class AppNextCodeController extends MappedCrudController<Long, AppNextCode, NextCodeDto, NextCodeDto, AppNextCodeService> {
}
