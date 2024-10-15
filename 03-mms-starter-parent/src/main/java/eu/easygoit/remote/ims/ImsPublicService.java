package eu.easygoit.remote.ims;

import eu.easygoit.api.PublicControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Ims public service.
 */
@FeignClient(configuration = FeignConfig.class, name = "identity-service", contextId = "ims-public", path = "/api/v1/public")
public interface ImsPublicService extends PublicControllerApi {

}
