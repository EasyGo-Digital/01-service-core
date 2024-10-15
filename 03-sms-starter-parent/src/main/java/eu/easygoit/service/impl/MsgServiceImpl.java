package eu.easygoit.service.impl;

import eu.easygoit.api.MailMessageControllerApi;
import eu.easygoit.com.camel.repository.ICamelRepository;
import eu.easygoit.dto.data.MailMessageDto;
import eu.easygoit.service.IMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Msg service.
 */
@Slf4j
@Service
public class MsgServiceImpl implements IMsgService {

    @Autowired
    private MailMessageControllerApi messageService;

    @Autowired
    private ICamelRepository camelRepository;

    @Override
    public void sendMessage(String senderDomainName, MailMessageDto mailMessage, boolean async) {
        if (async) {
            camelRepository.asyncSendBody(ICamelRepository.send_email_queue, mailMessage);
        } else {
            this.messageService.sendMail(senderDomainName, mailMessage.getTemplateName(), mailMessage);
        }
    }
}
