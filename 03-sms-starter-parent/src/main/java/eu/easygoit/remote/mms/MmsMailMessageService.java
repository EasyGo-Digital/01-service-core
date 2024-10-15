package eu.easygoit.remote.mms;

import eu.easygoit.api.MailMessageControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Mms mail message service.
 */
@FeignClient(configuration = FeignConfig.class, name = "messaging-service", contextId = "mms-email", path = "/api/v1/private/mail")
public interface MmsMailMessageService extends MailMessageControllerApi {
}
