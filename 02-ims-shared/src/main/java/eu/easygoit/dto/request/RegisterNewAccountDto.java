package eu.easygoit.dto.request;

import eu.easygoit.constants.DomainConstants;
import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumAccountOrigin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Register new account dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RegisterNewAccountDto extends AbstractAuditableDto<Long> {

    @Builder.Default
    private String domain = DomainConstants.DEFAULT_DOMAIN_NAME;
    @Builder.Default
    private String origin = IEnumAccountOrigin.Types.SYS_ADMIN.name();

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String functionRole;
}
