package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.TokenConfigDto;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.mapper.TokenConfigMapper;
import eu.easygoit.model.TokenConfig;
import eu.easygoit.service.impl.TokenConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Token config controller.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v1/private/config/token")
@CtrlDef(handler = KmsExceptionHandler.class, mapper = TokenConfigMapper.class, minMapper = TokenConfigMapper.class, service = TokenConfigService.class)
public class TokenConfigController extends MappedCrudController<Long, TokenConfig, TokenConfigDto, TokenConfigDto, TokenConfigService> {
}
