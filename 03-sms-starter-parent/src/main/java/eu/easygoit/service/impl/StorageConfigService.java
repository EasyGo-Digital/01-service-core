package eu.easygoit.service.impl;

import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.dto.exception.StorageConfigNotFoundException;
import eu.easygoit.model.StorageConfig;
import eu.easygoit.repository.StorageConfigRepository;
import eu.easygoit.service.IMinIOApiService;
import eu.easygoit.service.IStorageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Storage config service.
 */
@Service
@Transactional
@SrvRepo(value = StorageConfigRepository.class)
public class StorageConfigService extends CrudService<Long, StorageConfig, StorageConfigRepository> implements IStorageConfigService {

    @Autowired
    private StorageConfigRepository storageConfigRepository;
    @Autowired
    private IMinIOApiService minIOApiService;

    @Override
    public StorageConfig findByDomainIgnoreCase(String domain) {
        Optional<StorageConfig> optional = storageConfigRepository.findFirstByDomainIgnoreCase(domain);
        if (!optional.isPresent()) {
            optional = storageConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        }

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new StorageConfigNotFoundException("for domain: " + domain);
        }
    }

    @Override
    public StorageConfig afterUpdate(StorageConfig storageConfig) {
        switch (storageConfig.getType()) {
            case MINIO_STORAGE: {
                minIOApiService.updateConnection(storageConfig);
            }
            break;
            case CEPH_STORAGE: {
                //minIOApiService.updateConnection(storageConfig);
            }
            break;
            case LAKEFS_STORAGE: {
                //minIOApiService.updateConnection(storageConfig);
            }
            break;
            case OPENIO_STORAGE: {
                //minIOApiService.updateConnection(storageConfig);
            }
            break;
        }
        return super.afterUpdate(storageConfig);
    }
}
