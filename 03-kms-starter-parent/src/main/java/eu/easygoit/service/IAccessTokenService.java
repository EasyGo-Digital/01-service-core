package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.enums.IEnumAppToken;
import eu.easygoit.model.AccessToken;

/**
 * The interface Access token service.
 */
public interface IAccessTokenService extends ICrudServiceMethod<Long, AccessToken> {

    /**
     * Find by application and account code and token and token type access token.
     *
     * @param application the application
     * @param accountCode the account code
     * @param token       the token
     * @param tokenType   the token type
     * @return the access token
     */
    AccessToken findByApplicationAndAccountCodeAndTokenAndTokenType(String application, String accountCode, String token, IEnumAppToken.Types tokenType);

    /**
     * Find by account code and token and token type access token.
     *
     * @param accountCode the account code
     * @param token       the token
     * @param tokenType   the token type
     * @return the access token
     */
    AccessToken findByAccountCodeAndTokenAndTokenType(String accountCode, String token, IEnumAppToken.Types tokenType);
}
