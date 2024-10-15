package eu.easygoit.dto.data;

import eu.easygoit.dto.IImageUploadDto;
import eu.easygoit.dto.common.TokenDto;
import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumBinaryStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Application dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApplicationDto extends AbstractAuditableDto<Long> implements IImageUploadDto {

    private String domain;
    private String code;
    @NotEmpty
    private String name;
    @NotEmpty
    private String title;
    private String description;
    @NotEmpty
    @Builder.Default
    private String category = "PRM Store";
    @NotEmpty
    private String url;
    private Integer order;
    private String imagePath;

    @Builder.Default
    private IEnumBinaryStatus.Types adminStatus = IEnumBinaryStatus.Types.ENABLED;

    //App authorization token
    private TokenDto token;
}
