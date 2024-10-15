package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.model.Account;
import eu.easygoit.repository.AccountRepository;
import eu.easygoit.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Account service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@SrvRepo(value = AccountRepository.class)
public class AccountService extends CrudService<Long, Account, AccountRepository> implements IAccountService {

    @Override
    public boolean checkIfExists(Account account, boolean createIfNotExists) {
        Optional<Account> optional = repository().findByCodeIgnoreCase(account.getCode());
        if (optional.isPresent()) {
            Account existing = optional.get();
            existing.setEmail(account.getEmail());
            existing.setAdminStatus(account.getAdminStatus());
            existing.setSystemStatus(account.getSystemStatus());
            existing.setFullName(account.getFullName());
            this.update(existing);
            return true;
        } else if (createIfNotExists) {
            //Create the account if not exists
            this.create(account);
            return true;
        }

        return false;
    }
}
