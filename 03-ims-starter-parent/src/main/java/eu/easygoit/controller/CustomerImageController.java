package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedImageController;
import eu.easygoit.dto.data.CustomerDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.CustomerMapper;
import eu.easygoit.model.Customer;
import eu.easygoit.service.impl.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Customer image controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = CustomerMapper.class, minMapper = CustomerMapper.class, service = CustomerService.class)
@RequestMapping(path = "/api/v1/private/customer")
public class CustomerImageController extends MappedImageController<Long, Customer, CustomerDto, CustomerDto, CustomerService> {

}
