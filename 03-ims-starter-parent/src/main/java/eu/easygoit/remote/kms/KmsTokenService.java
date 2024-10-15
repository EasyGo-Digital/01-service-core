package eu.easygoit.remote.kms;


import eu.easygoit.config.FeignConfig;
import eu.easygoit.service.TokenServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Kms token service.
 */
@FeignClient(configuration = FeignConfig.class, name = "key-service", contextId = "kms-token", path = "/api/v1/private/token")
public interface KmsTokenService extends TokenServiceApi {

}
