package eu.easygoit.dto.data;

import eu.easygoit.dto.extendable.ApiPermissionModelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * The type Api permission dto.
 */
@Data
@AllArgsConstructor
@SuperBuilder
public class ApiPermissionDto extends ApiPermissionModelDto<Long> {
}
