package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.model.Theme;


/**
 * The interface Theme service.
 */
public interface IThemeService extends ICrudServiceMethod<Long, Theme> {

    /**
     * Find theme by account code and domain code theme.
     *
     * @param accountCode the account code
     * @param domainCode  the domain code
     * @return the theme
     */
    Theme findThemeByAccountCodeAndDomainCode(String accountCode, String domainCode);

    /**
     * Update theme theme.
     *
     * @param theme the theme
     * @return the theme
     */
    Theme updateTheme(Theme theme);
}
