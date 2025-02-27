package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.enums.IEnumAppToken;
import eu.easygoit.model.TokenConfig;

/**
 * The interface Token config service.
 */
public interface ITokenConfigService extends ICrudServiceMethod<Long, TokenConfig> {

    /**
     * Build token config token config.
     *
     * @param domain    the domain
     * @param tokenType the token type
     * @return the token config
     */
    TokenConfig buildTokenConfig(String domain, IEnumAppToken.Types tokenType);
}
