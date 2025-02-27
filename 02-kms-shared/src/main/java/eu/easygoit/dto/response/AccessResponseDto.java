package eu.easygoit.dto.response;


import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumPasswordStatus;
import eu.easygoit.enums.IEnumWebToken;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Access response dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccessResponseDto extends AbstractAuditableDto<Long> {

    @NotNull
    private IEnumPasswordStatus.Types status;
    @NotNull
    private IEnumWebToken.Types tokenType;
    @NotEmpty
    private String accessToken;
    @NotEmpty
    private String refreshToken;
    @NotEmpty
    private String authorityToken;
}
