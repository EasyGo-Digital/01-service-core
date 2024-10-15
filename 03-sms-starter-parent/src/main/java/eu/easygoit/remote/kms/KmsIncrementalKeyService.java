package eu.easygoit.remote.kms;

import eu.easygoit.api.IncrementalKeyControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Kms incremental key service.
 */
@FeignClient(configuration = FeignConfig.class, name = "key-service", contextId = "incremental-key", path = "/api/v1/private/key")
public interface KmsIncrementalKeyService extends IncrementalKeyControllerApi {

}
