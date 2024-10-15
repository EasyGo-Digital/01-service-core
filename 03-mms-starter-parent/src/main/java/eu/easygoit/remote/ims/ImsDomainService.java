package eu.easygoit.remote.ims;

import eu.easygoit.api.DomainControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Ims domain service.
 */
@FeignClient(configuration = FeignConfig.class, name = "identity-service", contextId = "ims-domain", path = "/api/v1/private/domain")
public interface ImsDomainService extends DomainControllerApi {

}
