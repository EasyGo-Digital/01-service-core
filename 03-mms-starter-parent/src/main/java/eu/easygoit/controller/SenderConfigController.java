package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.SenderConfigDto;
import eu.easygoit.exception.handler.MmsExceptionHandler;
import eu.easygoit.factory.SenderFactory;
import eu.easygoit.mapper.SenderConfigMapper;
import eu.easygoit.model.SenderConfig;
import eu.easygoit.service.impl.SenderConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Sender config controller.
 */
//http://localhost:8060/webjars/swagger-ui/index.html#/
//http://localhost:8060/messaging/mms/private/account
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v1/private/config/mail")
@CtrlDef(handler = MmsExceptionHandler.class, mapper = SenderConfigMapper.class, minMapper = SenderConfigMapper.class, service = SenderConfigService.class)
public class SenderConfigController extends MappedCrudController<Long, SenderConfig, SenderConfigDto, SenderConfigDto, SenderConfigService> {

    @Autowired
    private SenderFactory senderFactory;

    @Override
    public SenderConfig afterUpdate(SenderConfig senderConfig) {
        senderFactory.removeSender(senderConfig.getDomain());
        return super.afterUpdate(senderConfig);
    }
}
