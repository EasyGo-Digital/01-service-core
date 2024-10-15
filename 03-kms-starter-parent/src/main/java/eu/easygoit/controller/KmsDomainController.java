package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.api.KmsDomainControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.constants.CtrlConstants;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.KmsDomainDto;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.exception.handler.KmsExceptionHandler;
import eu.easygoit.mapper.DomainMapper;
import eu.easygoit.model.KmsDomain;
import eu.easygoit.service.impl.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Kms domain controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = KmsExceptionHandler.class, mapper = DomainMapper.class, minMapper = DomainMapper.class, service = DomainService.class)
@RequestMapping(path = "/api/v1/private/domain")
public class KmsDomainController extends MappedCrudController<Long, KmsDomain, KmsDomainDto, KmsDomainDto, DomainService>
        implements KmsDomainControllerApi {

    @Override
    public ResponseEntity<KmsDomainDto> updateAdminStatus(RequestContextDto requestContext,
                                                          String domain,
                                                          IEnumBinaryStatus.Types newStatus) {
        log.info("in update status");
        try {
            return ResponseFactory.ResponseOk(mapper().entityToDto(crudService().updateAdminStatus(domain, newStatus)));
        } catch (Throwable e) {
            log.error("<Error>: update Domain Status : {} ", e);
            return getBackExceptionResponse(e);
        }
    }

    @Override
    public ResponseEntity<Boolean> updateDomain(//RequestContextDto requestContext,
                                                KmsDomainDto domain) {
        log.info("Call update domain " + domain.toString());
        try {
            return ResponseFactory.ResponseOk(crudService().checkIfExists(mapper().dtoToEntity(domain),
                    true));
        } catch (Throwable e) {
            log.error(CtrlConstants.ERROR_API_EXCEPTION, e);
            return getBackExceptionResponse(e);
        }
    }
}
