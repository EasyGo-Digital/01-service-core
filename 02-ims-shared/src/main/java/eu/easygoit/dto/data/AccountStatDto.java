package eu.easygoit.dto.data;


import eu.easygoit.dto.extendable.IdentifiableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * The type Account stat dto.
 */
@Data
@AllArgsConstructor
@SuperBuilder
public class AccountStatDto extends IdentifiableDto<Long> {

}
