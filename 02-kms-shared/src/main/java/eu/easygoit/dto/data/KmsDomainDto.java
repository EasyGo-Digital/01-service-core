package eu.easygoit.dto.data;


import eu.easygoit.dto.extendable.DomainModelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * The type Kms domain dto.
 */
@Data
@AllArgsConstructor
@SuperBuilder
public class KmsDomainDto extends DomainModelDto<Long> {

}
