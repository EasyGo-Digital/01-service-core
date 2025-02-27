package eu.easygoit.dto.data;


import eu.easygoit.dto.extendable.AbstractAuditableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type App parameter dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AppParameterDto extends AbstractAuditableDto<Long> {

    private String name;
    private String value;
    private String domain;
    private String description;
}
