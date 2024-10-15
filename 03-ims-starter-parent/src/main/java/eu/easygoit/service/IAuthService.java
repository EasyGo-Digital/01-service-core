package eu.easygoit.service;

import eu.easygoit.dto.request.RequestTrackingDto;
import eu.easygoit.dto.response.AuthResponseDto;
import eu.easygoit.enums.IEnumAuth;
import eu.easygoit.model.RegistredUser;

/**
 * The interface Auth service.
 */
public interface IAuthService {

    /**
     * Register new account boolean.
     *
     * @param registredNewAccount the registred new account
     * @return the boolean
     */
    boolean registerNewAccount(RegistredUser registredNewAccount);

    /**
     * Authenticate auth response dto.
     *
     * @param requestTracking the request tracking
     * @param domain          the domain
     * @param userName        the user name
     * @param application     the application
     * @param password        the password
     * @param authType        the auth type
     * @return the auth response dto
     */
    AuthResponseDto authenticate(RequestTrackingDto requestTracking, String domain, String userName, String application, String password, IEnumAuth.Types authType);
}
