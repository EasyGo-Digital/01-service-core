package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.MsgTemplateDto;
import eu.easygoit.dto.extendable.IdentifiableDto;
import eu.easygoit.enums.IEnumMsgTemplateName;
import eu.easygoit.exception.handler.MmsExceptionHandler;
import eu.easygoit.mapper.MsgTemplateMapper;
import eu.easygoit.model.MsgTemplate;
import eu.easygoit.service.impl.MsgTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * The type Template controller.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v1/private/mail/template")
@CtrlDef(handler = MmsExceptionHandler.class, mapper = MsgTemplateMapper.class, minMapper = MsgTemplateMapper.class, service = MsgTemplateService.class)
public class MsgTemplateController extends MappedCrudController<Long, MsgTemplate, MsgTemplateDto, MsgTemplateDto, MsgTemplateService> {

    /**
     * Get template names response entity.
     *
     * @return the response entity
     */
    @Operation(summary = "get Template Names Api",
            description = "get Template Names")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @GetMapping(path = "/names")
    public ResponseEntity<List<String>> getTemplateNames() {
        try {
            return ResponseFactory.ResponseOk(Arrays.stream(Arrays.stream(IEnumMsgTemplateName.Types.class.getEnumConstants()).map(Enum::name)
                            .toArray(String[]::new))
                    .toList());
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
