package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.com.rest.service.IFileServiceMethods;
import eu.easygoit.enums.IEnumMsgTemplateName;
import eu.easygoit.model.MsgTemplate;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;


/**
 * The interface Template service.
 */
public interface IMsgTemplateService extends ICrudServiceMethod<Long, MsgTemplate>,
        IFileServiceMethods<Long, MsgTemplate> {

    /**
     * Compose message body string.
     *
     * @param senderDomainName the sender domain name
     * @param templateName     the template name
     * @param variables        the variables
     * @return the string
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     */
    String composeMessageBody(String senderDomainName,
                              IEnumMsgTemplateName.Types templateName,
                              Map<String, String> variables) throws IOException, TemplateException;
}
