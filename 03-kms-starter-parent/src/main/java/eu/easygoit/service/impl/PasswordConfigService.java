package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CodifiableService;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.model.PasswordConfig;
import eu.easygoit.model.schema.SchemaColumnConstantName;
import eu.easygoit.repository.PasswordConfigRepository;
import eu.easygoit.service.IPasswordConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Password config service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@SrvRepo(value = PasswordConfigRepository.class)
public class PasswordConfigService extends CodifiableService<Long, PasswordConfig, PasswordConfigRepository> implements IPasswordConfigService {

    @Override
    public AppNextCode initCodeGenerator() {
        return AppNextCode.builder()
                .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                .entity(PasswordConfig.class.getSimpleName())
                .attribute(SchemaColumnConstantName.C_CODE)
                .prefix("PWD")
                .valueLength(6L)
                .value(1L)
                .increment(1)
                .build();
    }
}
