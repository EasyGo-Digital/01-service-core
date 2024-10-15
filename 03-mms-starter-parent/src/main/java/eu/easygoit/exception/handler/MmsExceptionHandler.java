package eu.easygoit.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.easygoit.api.AppParameterControllerApi;
import eu.easygoit.api.MailMessageControllerApi;
import eu.easygoit.config.AppProperties;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.dto.data.MailMessageDto;
import eu.easygoit.enums.IEnumMsgTemplateName;
import eu.easygoit.types.EmailSubjects;
import eu.easygoit.types.MsgTemplateVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * The type Mms exception handler.
 */
@Slf4j
@Component
public class MmsExceptionHandler extends ControllerExceptionHandler {

    private final AppProperties appProperties;

    @Autowired
    private MailMessageControllerApi msgService;
    @Autowired
    private AppParameterControllerApi appParameterService;

    public MmsExceptionHandler(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public void processUnmanagedException(String message) {
        //Email message error to system admin
        log.error(message);
        try {
            ResponseEntity<String> result = appParameterService.getTechnicalAdminEmail();
            if (result.getStatusCode().is2xxSuccessful() && result.hasBody() && StringUtils.hasText(result.getBody())) {
                String techAdminEmail = result.getBody();
                MailMessageDto mailMessageDto = null;
                try {
                    mailMessageDto = MailMessageDto.builder()
                            .subject(EmailSubjects.UNMANAGED_EXCEPTION)
                            .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                            .toAddr(techAdminEmail)
                            .templateName(IEnumMsgTemplateName.Types.UNMANAGED_EXCEPTION_TEMPLATE)
                            .variables(MailMessageDto.getVariablesAsString(Map.of(
                                    //Common vars
                                    MsgTemplateVariables.V_EXCEPTION, message
                            )))
                            .build();
                } catch (JsonProcessingException e) {
                    log.error("<Error>: send unmanaged exception email : {} ", e);
                }
                //Send the email message
                msgService.sendMail(DomainConstants.SUPER_DOMAIN_NAME, mailMessageDto.getTemplateName(), mailMessageDto);
            } else {
                log.error("<Error>: technical email not found");
            }
        } catch (Exception e) {
            log.error("Remote feign call failed : ", e);
            //throw new RemoteCallFailedException(e);
        }

    }
}
