package eu.easygoit.service.impl;

import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.model.SenderConfig;
import eu.easygoit.repository.SenderConfigRepository;
import eu.easygoit.service.ISenderConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Sender config service.
 */
@Service
@Transactional
@SrvRepo(value = SenderConfigRepository.class)
public class SenderConfigService extends CrudService<Long, SenderConfig, SenderConfigRepository> implements ISenderConfigService {

}
