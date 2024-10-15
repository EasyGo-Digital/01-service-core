package eu.easygoit.service.impl;


import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudServiceUtils;
import eu.easygoit.exception.AccountNotFoundException;
import eu.easygoit.exception.PropertyNotFoundException;
import eu.easygoit.model.Account;
import eu.easygoit.model.AccountProps;
import eu.easygoit.model.Property;
import eu.easygoit.repository.AccountRepository;
import eu.easygoit.repository.AcountPropsRepository;
import eu.easygoit.repository.PropertyRepository;
import eu.easygoit.service.IPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * The type Property service.
 */
@Slf4j
@Service
@Transactional
@SrvRepo(value = PropertyRepository.class)
public class PropertyService extends CrudServiceUtils<Property, PropertyRepository> implements IPropertyService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AcountPropsRepository acountPropsRepository;


    @Override
    public Property updateProperty(String accountCode, Property property) {
        Optional<Account> optionalAccount = accountRepository.findByCodeIgnoreCase(accountCode);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Optional<Property> optional = repository().findByAccountAndGuiNameAndName(accountCode, property.getGuiName(), property.getName());
            if (optional.isPresent()) {
                // var .get
                optional.get().setValue(property.getValue());
                return repository().save(optional.get());
            } else {
                Optional<AccountProps> accountPropsOptional = acountPropsRepository.findByAccount_CodeIgnoreCase(accountCode);
                if (accountPropsOptional.isPresent()) {
                    AccountProps accountProps = accountPropsOptional.get();
                    if (CollectionUtils.isEmpty(accountProps.getProperties())) {
                        accountProps.setProperties(new ArrayList<>());
                    }
                    accountProps.getProperties().add(property);
                    acountPropsRepository.save(accountProps);
                } else {
                    AccountProps accountProps = AccountProps.builder()
                            .account(account)
                            .properties(Arrays.asList(property))
                            .build();
                    acountPropsRepository.save(accountProps);
                }

                return property;
            }
        } else {
            throw new AccountNotFoundException("with code: " + accountCode);
        }
    }

    @Override
    public Property getPropertyByAccount(String accountCode, String guiName, String name) {
        Optional<Account> optionalAccount = accountRepository.findByCodeIgnoreCase(accountCode);
        if (optionalAccount.isPresent()) {
            Optional<Property> optional = repository().findByAccountAndGuiNameAndName(accountCode, guiName, name);
            if (optional.isPresent()) {
                return optional.get();
            }
            throw new PropertyNotFoundException("with name: " + accountCode + "/" + guiName + "/" + name);
        } else {
            throw new AccountNotFoundException("with code: " + accountCode);
        }
    }

    @Override
    public List<Property> getPropertyByAccountAndGui(String accountCode, String guiName) {
        Optional<Account> optionalAccount = accountRepository.findByCodeIgnoreCase(accountCode);
        if (optionalAccount.isPresent()) {
            List<Property> listProperty = repository().findByAccountAndGuiName(accountCode, guiName);
            if (listProperty != null) {
                return listProperty;
            }
            throw new PropertyNotFoundException("with name: " + accountCode + "/" + guiName);
        } else {
            throw new AccountNotFoundException("with code: " + accountCode);
        }
    }
}
