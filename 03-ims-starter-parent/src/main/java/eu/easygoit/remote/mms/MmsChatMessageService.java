package eu.easygoit.remote.mms;

import eu.easygoit.api.ChatMessageControllerApi;
import eu.easygoit.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Mms chat message service.
 */
@FeignClient(configuration = FeignConfig.class, name = "messaging-service", contextId = "mms-chat", path = "/api/v1/private/chat")
public interface MmsChatMessageService extends ChatMessageControllerApi {
}
