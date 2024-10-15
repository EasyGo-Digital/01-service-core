package eu.easygoit.dto.data;

import eu.easygoit.dto.IFileUploadDto;
import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Template dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MsgTemplateDto extends AbstractAuditableDto<Long> implements IFileUploadDto {

    private String domain;
    private String name;
    private String code;
    private String description;
    private String path;
    private String fileName;
    private String originalFileName;
    private MultipartFile file;
    @Builder.Default
    private IEnumLanguage.Types language = IEnumLanguage.Types.EN;
}
