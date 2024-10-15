package eu.easygoit.async.camel.processor;

import eu.easygoit.com.camel.processor.AbstractCamelProcessor;
import eu.easygoit.dto.wsocket.WsConnectDto;
import eu.easygoit.dto.wsocket.WsMessageDto;
import eu.easygoit.dto.wsocket.WsMessageWrapperDto;
import eu.easygoit.enums.IEnumWSBroker;
import eu.easygoit.enums.IEnumWSEndpoint;
import eu.easygoit.enums.IEnumWSMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The type Ws chat connect processor.
 */
@Slf4j
@Component
@Qualifier("wsChatConnectProcessor")
public class WsChatConnectProcessor extends AbstractCamelProcessor<WsConnectDto> {

    @Override
    public void performProcessor(Exchange exchange, WsConnectDto wsConnect) throws Exception {
        WsMessageWrapperDto message = WsMessageWrapperDto.builder()
                .endPoint(IEnumWSEndpoint.Types.CHAT)
                .broker(IEnumWSBroker.Types.GROUP)
                .senderId(wsConnect.getSenderId())
                .message(WsMessageDto.builder()
                        .type(IEnumWSMessage.Types.STATUS)
                        .senderId(wsConnect.getSenderId())
                        .content(wsConnect.getStatus().name())
                        .build())
                .build();

        exchange.getIn().setHeader("receiverId", wsConnect.getGroupId());
        exchange.getIn().setBody(message);
        exchange.getIn().setHeader(AbstractCamelProcessor.RETURN_HEADER, true);
    }
}
