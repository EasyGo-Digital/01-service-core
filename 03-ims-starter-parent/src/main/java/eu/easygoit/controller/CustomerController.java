package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.CustomerDto;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.CustomerMapper;
import eu.easygoit.model.Customer;
import eu.easygoit.service.ICustomerService;
import eu.easygoit.service.impl.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Customer controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = CustomerMapper.class, minMapper = CustomerMapper.class, service = CustomerService.class)
@RequestMapping(path = "/api/v1/private/customer")
public class CustomerController extends MappedCrudController<Long, Customer, CustomerDto, CustomerDto, CustomerService> {

    @Autowired
    private ICustomerService customerService;

    /**
     * Update customer status response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @param newStatus      the new status
     * @return the response entity
     */
    @Operation(summary = "Update customer status Api",
            description = "Update customer status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))})
    })
    @PutMapping(path = "/update-status")
    public ResponseEntity<CustomerDto> updateCustomerStatus(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                            @RequestParam(name = RestApiConstants.ID) Long id,
                                                            @RequestParam(name = RestApiConstants.NEW_STATUS) IEnumBinaryStatus.Types newStatus) {
        log.info("in update status");
        try {
            return ResponseFactory.ResponseOk(mapper().entityToDto(crudService().updateStatus(id, newStatus)));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    /**
     * Link to existing account response entity.
     *
     * @param requestContext the request context
     * @param id             the id
     * @param accountCode    the account code
     * @return the response entity
     */
    @Operation(summary = "Link customer to existing account Api",
            description = "Link customer to existing account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))})
    })
    @PutMapping(path = "/link-account")
    public ResponseEntity<CustomerDto> LinkToExistingAccount(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                             @RequestParam(name = RestApiConstants.ID) Long id,
                                                             @RequestParam(name = RestApiConstants.ACCOUNT_CODE) String accountCode) {
        log.info("Link to existing account");
        try {
            return ResponseFactory.ResponseOk(mapper().entityToDto(crudService().linkToAccount(id, accountCode)));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    /**
     * Gets name customer.
     *
     * @param requestContext the request context
     * @return the name customer
     */
    @Operation(summary = "Get customers name list Api",
            description = "Get customers name list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping(path = "/names")
    public ResponseEntity<List<String>> getCustomersNames(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext) {
        try {
            return ResponseFactory.ResponseOk(customerService.getNames());
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
