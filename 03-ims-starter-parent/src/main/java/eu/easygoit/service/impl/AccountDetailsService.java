package eu.easygoit.service.impl;

import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.model.AccountDetails;
import eu.easygoit.repository.AccountDetailsRepository;
import eu.easygoit.service.IAccountDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Account details service.
 */
@Service
@Transactional
@SrvRepo(value = AccountDetailsRepository.class)
public class AccountDetailsService extends CrudService<Long, AccountDetails, AccountDetailsRepository> implements IAccountDetailsService {
}
