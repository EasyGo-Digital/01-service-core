package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.model.AppParameter;


/**
 * The interface App parameter service.
 */
public interface IAppParameterService extends ICrudServiceMethod<Long, AppParameter> {

    /**
     * Gets value by domain and name.
     *
     * @param domain       the domain
     * @param name         the name
     * @param allowDefault the allow default
     * @param defaultValue the default value
     * @return the value by domain and name
     */
    String getValueByDomainAndName(String domain, String name, boolean allowDefault, String defaultValue);

    /**
     * Gets technical admin email.
     *
     * @return the technical admin email
     */
    String getTechnicalAdminEmail();
}
