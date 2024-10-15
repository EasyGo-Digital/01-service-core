package eu.easygoit.remote.ims;

import eu.easygoit.api.AppParameterControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Ims app parameter service.
 */
@FeignClient(configuration = FeignConfig.class, name = "identity-service", contextId = "ims-app-parameter", path = "/api/v1/private/appParameter")
public interface ImsAppParameterService extends AppParameterControllerApi {

}
