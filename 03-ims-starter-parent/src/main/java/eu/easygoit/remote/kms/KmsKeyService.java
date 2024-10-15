package eu.easygoit.remote.kms;

import eu.easygoit.config.FeignConfig;
import eu.easygoit.service.KeyServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Kms key service.
 */
@FeignClient(configuration = FeignConfig.class, name = "key-service", contextId = "kms-key", path = "/api/v1/private/key")
public interface KmsKeyService extends KeyServiceApi {

}
