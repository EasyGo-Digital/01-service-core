package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CodifiableService;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.model.DigestConfig;
import eu.easygoit.model.schema.SchemaColumnConstantName;
import eu.easygoit.repository.DigesterConfigRepository;
import eu.easygoit.service.IDigestConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Digest config service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@SrvRepo(value = DigesterConfigRepository.class)
public class DigestConfigService extends CodifiableService<Long, DigestConfig, DigesterConfigRepository> implements IDigestConfigService {

    @Override
    public AppNextCode initCodeGenerator() {
        return AppNextCode.builder()
                .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                .entity(DigestConfig.class.getSimpleName())
                .attribute(SchemaColumnConstantName.C_CODE)
                .prefix("DIG")
                .valueLength(6L)
                .value(1L)
                .increment(1)
                .build();
    }
}
