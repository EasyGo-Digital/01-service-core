package eu.easygoit.service.impl;

import eu.easygoit.constants.DomainConstants;
import eu.easygoit.exception.SenderConfigNotFoundException;
import eu.easygoit.model.SenderConfig;
import eu.easygoit.repository.SenderConfigRepository;
import eu.easygoit.service.IMailSenderFactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * The type Mail sender factory service service.
 */
@Slf4j
@Service
@Transactional
public class MailSenderFactoryServiceService implements IMailSenderFactoryService {

    @Autowired
    private SenderConfigRepository senderConfigRepository;

    @Autowired
    private Map<String, MailSender> mailSenders;

    /**
     * Sender from config mail sender.
     *
     * @param domain the domain
     * @return the mail sender
     */
    public MailSender senderFromConfig(String domain) {
        Optional<SenderConfig> optional = senderConfigRepository.findFirstByDomainIgnoreCase(domain);
        if (!optional.isPresent()) {
            optional = senderConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        }

        if (optional.isPresent()) {
            SenderConfig senderConfig = optional.get();

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(senderConfig.getHost());
            mailSender.setPort(Integer.valueOf(senderConfig.getPort()));
            mailSender.setUsername(senderConfig.getUsername());
            mailSender.setPassword(senderConfig.getPassword());
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", senderConfig.getTransportProtocol());
            props.put("mail.smtp.auth", senderConfig.getSmtpAuth());
            props.put("mail.smtp.starttls.enable", senderConfig.getSmtpStarttlsEnable());
            props.put("mail.smtp.starttls.required", senderConfig.getSmtpStarttlsRequired());
            props.put("mail.debug", senderConfig.getDebug());
            return mailSender;
        }

        throw new SenderConfigNotFoundException("for domain: " + domain);
    }

    /**
     * Gets sender.
     *
     * @param domain the domain
     * @return the sender
     */
    public MailSender getSender(String domain) {
        if (mailSenders.containsKey(domain)) {
            return mailSenders.get(domain);
        }

        MailSender mailSender = senderFromConfig(domain);
        mailSenders.put(domain, mailSender);
        return mailSender;
    }
}
