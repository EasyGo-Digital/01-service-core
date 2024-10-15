package eu.easygoit.dto.data;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * The type Storage config dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StorageConfigDto extends AbstractAuditableDto<Long> {

    private String domain;
    private IEnumStorage.Types type;
    private String userName;
    private String password;
    private String url;
}
