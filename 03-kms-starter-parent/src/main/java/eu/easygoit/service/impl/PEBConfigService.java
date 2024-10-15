package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CodifiableService;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.model.PEBConfig;
import eu.easygoit.model.schema.SchemaColumnConstantName;
import eu.easygoit.repository.PEBConfigRepository;
import eu.easygoit.service.IPEBConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Peb config service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@SrvRepo(value = PEBConfigRepository.class)
public class PEBConfigService extends CodifiableService<Long, PEBConfig, PEBConfigRepository> implements IPEBConfigService {

    @Override
    public AppNextCode initCodeGenerator() {
        return AppNextCode.builder()
                .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                .entity(PEBConfig.class.getSimpleName())
                .attribute(SchemaColumnConstantName.C_CODE)
                .prefix("PEB")
                .valueLength(6L)
                .value(1L)
                .increment(1)
                .build();
    }
}
