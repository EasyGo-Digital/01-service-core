package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedImageController;
import eu.easygoit.dto.data.DomainDto;
import eu.easygoit.dto.data.KmsDomainDto;
import eu.easygoit.exception.handler.ImsExceptionHandler;
import eu.easygoit.mapper.DomainMapper;
import eu.easygoit.model.Domain;
import eu.easygoit.remote.kms.KmsDomainService;
import eu.easygoit.service.impl.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;

/**
 * The type Domain image controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = ImsExceptionHandler.class, mapper = DomainMapper.class, minMapper = DomainMapper.class, service = DomainService.class)
@RequestMapping(path = "/api/v1/private/domain")
public class DomainImageController extends MappedImageController<Long, Domain, DomainDto, DomainDto, DomainService> {

    @Autowired
    private KmsDomainService kmsDomainService;

    @Override
    public Domain afterUpdate(Domain domain) throws Exception {
        try {
            ResponseEntity<Boolean> result = kmsDomainService.updateDomain(//RequestContextDto.builder().build(),
                    KmsDomainDto.builder()
                            .name(domain.getName())
                            .description(domain.getDescription())
                            .url(domain.getUrl())
                            .adminStatus(domain.getAdminStatus())
                            .build());
            if (result.getStatusCode().is2xxSuccessful() && result.hasBody() && result.getBody()) {
                return super.afterUpdate(domain);
            }
        } catch (Exception e) {
            log.error("Remote feign call failed : ", e);
            //throw new RemoteCallFailedException(e);
        }

        throw new AccountException("Update domain issue in KMS");
    }
}
