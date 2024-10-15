package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.DigestConfigDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.mapper.DigestConfigMapper;
import eu.easygoit.model.DigestConfig;
import eu.easygoit.service.impl.DigestConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Digest config controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = KmsExceptionHandler.class, mapper = DigestConfigMapper.class, minMapper = DigestConfigMapper.class, service = DigestConfigService.class)
@RequestMapping(path = "/api/v1/private/config/digest")
public class DigestConfigController extends MappedCrudController<Long, DigestConfig, DigestConfigDto, DigestConfigDto, DigestConfigService> {
}
