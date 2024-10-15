package eu.easygoit.exception;


import eu.easygoit.annotation.MsgLocale;


/**
 * The type Account not found exception.
 */
@MsgLocale("account.not.found.exception")
public class AccountNotFoundException extends ManagedException {

    /**
     * Instantiates a new Account not found exception.
     *
     * @param s the s
     */
    public AccountNotFoundException(String s) {
        super(s);
    }
}
