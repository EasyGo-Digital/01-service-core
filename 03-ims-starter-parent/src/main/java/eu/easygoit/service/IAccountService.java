package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.com.rest.service.IImageServiceMethods;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.data.*;
import eu.easygoit.dto.request.AccountAuthTypeRequest;
import eu.easygoit.dto.response.UserAccountDto;
import eu.easygoit.dto.response.UserContext;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.enums.IEnumLanguage;
import eu.easygoit.enums.IEnumSharedStatType;
import eu.easygoit.exception.AccountNotFoundException;
import eu.easygoit.model.Account;
import eu.easygoit.model.Application;
import eu.easygoit.model.ConnectionTracking;
import jakarta.transaction.NotSupportedException;

import java.util.List;

/**
 * The interface Account service.
 */
public interface IAccountService extends ICrudServiceMethod<Long, Account>, IImageServiceMethods<Long, Account> {

    /**
     * Find by domain and user name account.
     *
     * @param domain   the domain
     * @param userName the user name
     * @return the account
     */
    Account findByDomainAndUserName(String domain, String userName);

    /**
     * Find distinct allowed tools by domain and username list.
     *
     * @param domain   the domain
     * @param userName the username
     * @return the list
     */
    List<Application> findDistinctAllowedToolsByDomainAndUserName(String domain, String userName);

    /**
     * Update account admin status account.
     *
     * @param id        the id
     * @param newStatus the new status
     * @return the account
     */
    Account updateAccountAdminStatus(Long id, IEnumBinaryStatus.Types newStatus);

    /**
     * Update account is admin account.
     *
     * @param id        the id
     * @param newStatus the new status
     * @return the account
     */
    Account updateAccountIsAdmin(Long id, boolean newStatus);

    /**
     * Find emails by domain list.
     *
     * @param domain the domain
     * @return the list
     */
    List<String> findEmailsByDomain(String domain);

    /**
     * Build allowed tools list.
     *
     * @param account the account
     * @param token   the token
     * @return the list
     */
    List<ApplicationDto> buildAllowedTools(Account account, String token);


    /**
     * Gets min info by domain.
     *
     * @param domain the domain
     * @return the min info by domain
     * @throws NotSupportedException the not supported exception
     */
    List<MinAccountDto> getMinInfoByDomain(String domain) throws NotSupportedException;


    /**
     * Gets authentication type.
     *
     * @param accountAuthTypeRequest the account auth type request
     * @return the authentication type
     * @throws AccountNotFoundException the account not found exception
     */
    UserContext getAuthenticationType(AccountAuthTypeRequest accountAuthTypeRequest) throws AccountNotFoundException;

    /**
     * Switch auth type boolean.
     *
     * @param accountAuthTypeRequest the account auth type request
     * @return the boolean
     * @throws AccountNotFoundException the account not found exception
     */
    boolean switchAuthType(AccountAuthTypeRequest accountAuthTypeRequest) throws AccountNotFoundException;

    /**
     * Update language account.
     *
     * @param id       the id
     * @param language the language
     * @return the account
     */
    Account updateLanguage(Long id, IEnumLanguage.Types language);

    /**
     * Gets by domain.
     *
     * @param domain the domain
     * @return the by domain
     */
    List<Account> getByDomain(String domain);

    /**
     * Check if application allowed boolean.
     *
     * @param domain      the domain
     * @param userName    the user name
     * @param application the application
     * @return the boolean
     */
    boolean checkIfApplicationAllowed(String domain, String userName, String application);

    /**
     * Track user connections.
     *
     * @param domain             the domain
     * @param userName           the user name
     * @param connectionTracking the connection tracking
     */
    void trackUserConnections(String domain, String userName, ConnectionTracking connectionTracking);

    /**
     * Chat accounts by domain list.
     *
     * @param domain the domain
     * @return the list
     */
    List<Account> chatAccountsByDomain(String domain);

    /**
     * Resend creation email boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean resendCreationEmail(Long id);

    /**
     * Gets global statistics.
     *
     * @param statType       the stat type
     * @param requestContext the request context
     * @return the global statistics
     */
    AccountGlobalStatDto getGlobalStatistics(IEnumSharedStatType.Types statType, RequestContextDto requestContext);

    /**
     * Gets object statistics.
     *
     * @param code the code
     * @return the object statistics
     */
    AccountStatDto getObjectStatistics(String code);

    /**
     * Stat get confirmed resume accounts count long.
     *
     * @param requestContext the request context
     * @return the long
     */
    Long stat_GetConfirmedResumeAccountsCount(RequestContextDto requestContext);

    /**
     * Stat get confirmed employee accounts count long.
     *
     * @param requestContext the request context
     * @return the long
     */
    Long stat_GetConfirmedEmployeeAccountsCount(RequestContextDto requestContext);

    /**
     * Create domain admin account.
     *
     * @param domain the domain
     * @param admin  the admin
     * @return the account
     */
    Account createDomainAdmin(String domain, DomainAdminDto admin);

    /**
     * Gets authentication data.
     *
     * @param email the email
     * @return the authentication data
     * @throws AccountNotFoundException the account not found exception
     */
    List<UserAccountDto> getAvailableEmailAccounts(String email) throws AccountNotFoundException;
}
