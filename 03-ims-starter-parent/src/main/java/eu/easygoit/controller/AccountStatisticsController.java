package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.AccountGlobalStatDto;
import eu.easygoit.dto.data.AccountStatDto;
import eu.easygoit.enums.IEnumSharedStatType;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.service.IAccountService;
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


/**
 * The type Account statistics controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(ImsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/account/stat")
public class AccountStatisticsController extends ControllerExceptionHandler {

    @Autowired
    private IAccountService accountService;

    /**
     * Gets global statistics.
     *
     * @param requestContext the request context
     * @param statType       the stat type
     * @return the global statistics
     */
    @Operation(summary = "Get Global Statistics Api",
            description = "Get Global Statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountGlobalStatDto.class))})
    })
    @GetMapping(path = "/global")
    ResponseEntity<AccountGlobalStatDto> getGlobalStatistics(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                             @RequestParam(name = RestApiConstants.STAT_TYPE) IEnumSharedStatType.Types statType) {
        log.info("Get global statistics");
        try {
            return ResponseFactory.ResponseOk(accountService.getGlobalStatistics(statType, requestContext));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    /**
     * Gets object statistics.
     *
     * @param requestContext the request context
     * @param code           the code
     * @return the object statistics
     */
    @Operation(summary = "Get Object Statistics Api",
            description = "Get Object Statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountStatDto.class))})
    })
    @GetMapping(path = "/object")
    ResponseEntity<AccountStatDto> getObjectStatistics(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                       @RequestParam(name = RestApiConstants.CODE) String code) {
        log.info("Get object statistics with code: ", code);
        try {
            return ResponseFactory.ResponseOk(accountService.getObjectStatistics(code));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
