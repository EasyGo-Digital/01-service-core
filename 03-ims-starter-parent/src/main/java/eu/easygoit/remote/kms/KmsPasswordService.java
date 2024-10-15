package eu.easygoit.remote.kms;

import eu.easygoit.api.PasswordControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Kms password service.
 */
@FeignClient(configuration = FeignConfig.class, name = "key-service", contextId = "kms-password", path = "/api/v1/private/password")
public interface KmsPasswordService extends PasswordControllerApi {

}
