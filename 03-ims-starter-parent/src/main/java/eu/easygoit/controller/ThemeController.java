package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.constants.JwtConstants;
import eu.easygoit.constants.RestApiConstants;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.ThemeDto;
import eu.easygoit.dto.extendable.IdentifiableDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.ThemeMapper;
import eu.easygoit.model.Theme;
import eu.easygoit.service.IThemeService;
import eu.easygoit.service.impl.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type Theme controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = ThemeMapper.class, minMapper = ThemeMapper.class, service = ThemeService.class)
@RequestMapping(path = "/api/v1/private/theme")
public class ThemeController extends MappedCrudController<Long, Theme, ThemeDto, ThemeDto, ThemeService> {

    @Autowired
    private IThemeService themeService;
    @Autowired
    private ThemeMapper themeMapper;

    /**
     * Find theme by account code and domain code response entity.
     *
     * @param requestContext the request context
     * @param domainCode     the domain code
     * @param accountCode    the account code
     * @return the response entity
     */
    @Operation(summary = "findThemeByAccountCodeAndDomainCode Api",
            description = "findThemeByAccountCodeAndDomainCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @GetMapping(path = "/find/{domainCode}/{accountCode}")
    public ResponseEntity<ThemeDto> findThemeByAccountCodeAndDomainCode(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                                        @PathVariable(name = RestApiConstants.DOMAIN_CODE) String domainCode,
                                                                        @PathVariable(name = RestApiConstants.ACCOUNT_CODE) String accountCode) {
        try {
            Theme theme = themeService.findThemeByAccountCodeAndDomainCode(accountCode, domainCode);

            if (theme != null) {
                ThemeDto themeDto = themeMapper.entityToDto(theme);
                return new ResponseEntity<>(themeDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }

    /**
     * Update theme response entity.
     *
     * @param requestContext the request context
     * @param theme          the theme
     * @return the response entity
     */
    @Operation(summary = "updateTheme Api",
            description = "updateTheme")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Api executed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IdentifiableDto.class))})
    })
    @PutMapping
    public ResponseEntity<ThemeDto> updateTheme(@RequestAttribute(value = JwtConstants.JWT_USER_CONTEXT) RequestContextDto requestContext,
                                                @Valid @RequestBody ThemeDto theme) {
        try {
            Theme themeResult = themeService.updateTheme(themeMapper.dtoToEntity(theme));
            return new ResponseEntity<>(themeMapper.entityToDto(themeResult), HttpStatus.OK);
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}

