package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.model.Account;
import eu.easygoit.model.KmsDomain;
import eu.easygoit.repository.AccountRepository;
import eu.easygoit.repository.DomainRepository;
import eu.easygoit.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * The type Domain service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@SrvRepo(value = DomainRepository.class)
public class DomainService extends CrudService<Long, KmsDomain, DomainRepository> implements IDomainService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public KmsDomain checkDomainIfExists(String domainName, String domainUrl, boolean createIfNotExists) {
        Optional<KmsDomain> optional = repository().findByNameIgnoreCase(domainName);
        if (optional.isPresent()) {
            return optional.get();
        } else if (createIfNotExists) {
            //Create the domain if not exists
            return this.create(KmsDomain.builder()
                    .name(domainName)
                    .url(domainUrl)
                    .description(domainName)
                    .build());
        }

        return null;
    }

    @Override
    public KmsDomain findByNameIgnoreCase(String domainName) {
        Optional<KmsDomain> optional = repository().findByNameIgnoreCase(domainName);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    @Override
    public Account checkAccountIfExists(String domainName, String domainUrl, String email, String userName, String fullName, boolean createIfNotExists) {
        //Check domain if exists
        KmsDomain kmsDomain = this.checkDomainIfExists(domainName, domainUrl, createIfNotExists);
        if (kmsDomain == null) {
            return null;
        }

        //Check account if exists
        Optional<Account> optional = accountRepository.findByDomainIgnoreCaseAndCodeIgnoreCase(domainName, userName);
        if (optional.isPresent()) {
            //Update account email if changed
            Account account = optional.get();
            if (StringUtils.hasText(email) && !account.getEmail().equals(email)) {
                account.setEmail(email);
                accountRepository.save(account);
            }
            return account;
        }

        //Create the account if not exists
        if (createIfNotExists) {
            return accountRepository.save(Account.builder()
                    .code(userName)
                    .email(email)
                    .domain(domainName)
                    .fullName(fullName)
                    .build());
        }
        return null;
    }

    @Override
    public boolean checkIfExists(KmsDomain kmsDomain, boolean createIfNotExists) {
        Optional<KmsDomain> optional = repository().findByNameIgnoreCase(kmsDomain.getName());
        if (optional.isPresent()) {
            //Update the domain if not exists
            kmsDomain.setId(optional.get().getId());
            this.update(kmsDomain);
            return true;
        } else if (createIfNotExists) {
            //Create the domain if not exists
            this.create(kmsDomain);
            return true;
        }

        return false;
    }

    @Override
    public KmsDomain updateAdminStatus(String domain, IEnumBinaryStatus.Types newStatus) {
        repository().updateAdminStatus(domain, newStatus);
        return repository().findByNameIgnoreCase(domain).orElse(null);
    }

    @Override
    public boolean isEnabled(String domain) {
        return repository().getAdminStatus(domain) == IEnumBinaryStatus.Types.ENABLED;
    }
}
