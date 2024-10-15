package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenKms;
import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.ImageService;
import eu.easygoit.config.AppProperties;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.exception.AccountNotFoundException;
import eu.easygoit.exception.CustomerNotFoundException;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.model.Customer;
import eu.easygoit.model.schema.SchemaColumnConstantName;
import eu.easygoit.remote.kms.KmsIncrementalKeyService;
import eu.easygoit.repository.AccountRepository;
import eu.easygoit.repository.CustomerRepository;
import eu.easygoit.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Customer service.
 */
@Slf4j
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@CodeGenKms(value = KmsIncrementalKeyService.class)
@SrvRepo(value = CustomerRepository.class)
public class CustomerService extends ImageService<Long, Customer, CustomerRepository> implements ICustomerService {

    private final AppProperties appProperties;
    private final AccountRepository accountRepository;


    /**
     * Instantiates a new Customer service.
     *
     * @param appProperties     the app properties
     * @param accountRepository the account repository
     */
    public CustomerService(AppProperties appProperties, AccountRepository accountRepository) {
        this.appProperties = appProperties;
        this.accountRepository = accountRepository;
    }

    @Override
    public AppNextCode initCodeGenerator() {
        return AppNextCode.builder()
                .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                .entity(Customer.class.getSimpleName())
                .attribute(SchemaColumnConstantName.C_CODE)
                .prefix("CUS")
                .valueLength(6L)
                .value(1L)
                .build();
    }

    @Override
    public List<String> getNames() {
        return repository().findAll().stream().map(customer -> customer.getName()).toList();
    }

    @Override
    public Customer updateStatus(Long id, IEnumBinaryStatus.Types newStatus) {
        repository().updateAdminStatusById(id, newStatus);
        return repository().findById(id).orElse(null);
    }

    @Override
    public Customer linkToAccount(Long id, String accountCode) {
        Customer customer = this.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("with id:" + id);
        }

        if (!accountRepository.existsByCodeIgnoreCase(accountCode)) {
            throw new AccountNotFoundException("with code:" + accountCode);
        }

        customer.setAccountCode(accountCode);
        return this.update(customer);
    }

    @Override
    protected String getUploadDirectory() {
        return this.appProperties.getUploadDirectory();
    }
}
