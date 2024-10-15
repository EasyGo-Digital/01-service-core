package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.model.StorageConfig;

/**
 * The interface Storage config service.
 */
public interface IStorageConfigService extends ICrudServiceMethod<Long, StorageConfig> {

    /**
     * Find by domain ignore case storage config.
     *
     * @param domain the domain
     * @return the storage config
     */
    StorageConfig findByDomainIgnoreCase(String domain);
}
