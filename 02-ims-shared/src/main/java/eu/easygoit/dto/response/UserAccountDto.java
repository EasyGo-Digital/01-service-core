package eu.easygoit.dto.response;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type User Auth dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserAccountDto extends AbstractAuditableDto<Long> {

    @NotEmpty
    private String domain;
    @NotNull
    private Long domainId;
    @NotEmpty
    private String code;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String functionRole;
}
