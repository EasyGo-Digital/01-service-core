package eu.easygoit.dto.data;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumAppToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * The type Token config dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TokenConfigDto extends AbstractAuditableDto<Long> {

    private String domain;
    private String code;
    private IEnumAppToken.Types tokenType;
    private String issuer;
    private String audience;
    private String signatureAlgorithm;
    private String secretKey;
}
