package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenKms;
import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.ImageService;
import eu.easygoit.config.AppProperties;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.model.Application;
import eu.easygoit.model.schema.SchemaColumnConstantName;
import eu.easygoit.remote.kms.KmsIncrementalKeyService;
import eu.easygoit.repository.ApplicationRepository;
import eu.easygoit.service.IApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Application service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@CodeGenKms(value = KmsIncrementalKeyService.class)
@SrvRepo(value = ApplicationRepository.class)
public class ApplicationService extends ImageService<Long, Application, ApplicationRepository>
        implements IApplicationService {

    private final AppProperties appProperties;

    /**
     * Instantiates a new Application service.
     *
     * @param appProperties the app properties
     */
    public ApplicationService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public AppNextCode initCodeGenerator() {
        return AppNextCode.builder()
                .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                .entity(Application.class.getSimpleName())
                .attribute(SchemaColumnConstantName.C_CODE)
                .prefix("APP")
                .valueLength(6L)
                .value(1L)
                .increment(1)
                .build();
    }

    @Override
    protected String getUploadDirectory() {
        return this.appProperties.getUploadDirectory();
    }

    @Override
    public Application updateStatus(Long id, IEnumBinaryStatus.Types newStatus) {
        repository().updateAdminStatusById(id, newStatus);
        return repository().findById(id).orElse(null);
    }

    @Override
    public Application findByName(String name) {
        return repository().findByNameIgnoreCase(name);
    }
}
