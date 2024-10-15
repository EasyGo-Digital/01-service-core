package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedFileController;
import eu.easygoit.dto.data.MsgTemplateDto;
import eu.easygoit.exception.handler.MmsExceptionHandler;
import eu.easygoit.mapper.MsgTemplateMapper;
import eu.easygoit.model.MsgTemplate;
import eu.easygoit.service.impl.MsgTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Template controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = MmsExceptionHandler.class, mapper = MsgTemplateMapper.class, minMapper = MsgTemplateMapper.class, service = MsgTemplateService.class)
@RequestMapping(path = "/api/v1/private/mail/template")
public class MsgTemplateFileController extends MappedFileController<Long, MsgTemplate, MsgTemplateDto, MsgTemplateDto, MsgTemplateService> {


}
