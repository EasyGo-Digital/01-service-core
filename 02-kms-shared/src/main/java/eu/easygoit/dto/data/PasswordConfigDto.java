package eu.easygoit.dto.data;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumAuth;
import eu.easygoit.enums.IEnumCharSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * The type Password config dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PasswordConfigDto extends AbstractAuditableDto<Long> {

    private String code;
    private String domain;
    private IEnumAuth.Types type;
    private String pattern;
    private IEnumCharSet.Types charSetType;
    private String initial;
    private Integer minLenght;
    private Integer maxLenth;
    private Integer lifeTime;
}
