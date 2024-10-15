package eu.easygoit.service.impl;

import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.repository.AppNextCodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type App next code service.
 */
@Service
@Transactional
@SrvRepo(value = AppNextCodeRepository.class)
public class AppNextCodeService extends CrudService<Long, AppNextCode, AppNextCodeRepository> {

}
