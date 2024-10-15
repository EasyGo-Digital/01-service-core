package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.model.Account;

/**
 * The interface Account service.
 */
public interface IAccountService extends ICrudServiceMethod<Long, Account> {

    /**
     * Check if exists boolean.
     *
     * @param account           the account
     * @param createIfNotExists the create if not exists
     * @return the boolean
     */
    boolean checkIfExists(Account account, boolean createIfNotExists);
}
