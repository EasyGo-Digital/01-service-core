package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedImageController;
import eu.easygoit.dto.data.ApplicationDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.ApplicationMapper;
import eu.easygoit.model.Application;
import eu.easygoit.service.impl.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type App image controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = ApplicationMapper.class, minMapper = ApplicationMapper.class, service = ApplicationService.class)
@RequestMapping(path = "/api/v1/private/application")
public class AppImageController extends MappedImageController<Long, Application, ApplicationDto, ApplicationDto, ApplicationService> {

}
