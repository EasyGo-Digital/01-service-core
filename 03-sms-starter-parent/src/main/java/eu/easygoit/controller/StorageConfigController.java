package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.StorageConfigDto;
import eu.easygoit.dto.extendable.IdentifiableDto;
import eu.easygoit.exception.handler.SmsExceptionHandler;
import eu.easygoit.mapper.StorageConfigMapper;
import eu.easygoit.model.StorageConfig;
import eu.easygoit.service.impl.StorageConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type Storage config controller.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v1/private/storage/config")
@CtrlDef(handler = SmsExceptionHandler.class, mapper = StorageConfigMapper.class, minMapper = StorageConfigMapper.class, service = StorageConfigService.class)
public class StorageConfigController extends MappedCrudController<Long, StorageConfig, StorageConfigDto, StorageConfigDto, StorageConfigService> {


    /**
     * Find by domain ignore case response entity.
     *
     * @param requestContext the request context
     * @param domain         the domain
     * @return the response entity
     */
    @Operation(summary = "findByDomainIgnoreCase Api",
            description = "findByDomainIgnoreCase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @GetMapping(path = "/domain/{domain}")
    public ResponseEntity<StorageConfigDto> findByDomainIgnoreCase(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                                   @PathVariable(name = RestApiConstants.DOMAIN_NAME) String domain) {
        try {
            return ResponseFactory.ResponseOk(this.mapper().entityToDto(crudService().findByDomainIgnoreCase(domain)));
        } catch (Throwable e) {
            log.error("<Error>: Error calling api getNotificationsByReceiverId : {}", e);
            return getBackExceptionResponse(e);
        }
    }
}
