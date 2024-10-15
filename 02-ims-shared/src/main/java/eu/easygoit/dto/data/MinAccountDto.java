package eu.easygoit.dto.data;


import eu.easygoit.constants.AccountTypeConstants;
import eu.easygoit.dto.extendable.AccountModelDto;
import eu.easygoit.enums.IEnumAccountSystemStatus;
import eu.easygoit.enums.IEnumAuth;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.enums.IEnumWSStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * The type Min account dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MinAccountDto extends AccountModelDto<Long> {

    @NotEmpty
    private String domain;
    private String imagePath;
    private String functionRole;
    private Boolean isAdmin;
    private Date lastConnectionDate;
    @Builder.Default
    private IEnumBinaryStatus.Types adminStatus = IEnumBinaryStatus.Types.ENABLED;
    @Builder.Default
    private IEnumAccountSystemStatus.Types systemStatus = IEnumAccountSystemStatus.Types.IDLE;

    private IEnumAuth.Types authType;
    @Builder.Default
    private String accountType = AccountTypeConstants.DOMAIN_USER;

    private AccountDetailsDto accountDetails;

    //Chat status info
    @Builder.Default
    private IEnumWSStatus.Types chatStatus = IEnumWSStatus.Types.DISCONNECTED;

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return this.getAccountDetails() != null ? this.accountDetails.getFullName() : null;
    }
}
