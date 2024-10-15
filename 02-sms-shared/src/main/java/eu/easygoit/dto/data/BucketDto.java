package eu.easygoit.dto.data;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * The type Bucket dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BucketDto extends AbstractAuditableDto<Long> {

    private String name;
    private LocalDateTime creationDate;
}
