package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.AccountDetailsDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.AccountDetailsMapper;
import eu.easygoit.model.AccountDetails;
import eu.easygoit.service.impl.AccountDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Account details controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = AccountDetailsMapper.class, minMapper = AccountDetailsMapper.class, service = AccountDetailsService.class)
@RequestMapping(path = "/api/v1/private/account/details")
public class AccountDetailsController extends MappedCrudController<Long, AccountDetails, AccountDetailsDto, AccountDetailsDto, AccountDetailsService> {
}
