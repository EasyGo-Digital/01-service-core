package eu.easygoit.dto.response;


import eu.easygoit.enums.IEnumAuth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User context.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContext {
    /**
     * The Otp length.
     */
    int otpLength;
    /**
     * The Auth type mode.
     */
    IEnumAuth.Types authTypeMode;
    /**
     * The Qr code token.
     */
    String qrCodeToken;

}
