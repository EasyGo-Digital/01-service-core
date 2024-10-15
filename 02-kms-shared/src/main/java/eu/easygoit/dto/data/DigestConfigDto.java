package eu.easygoit.dto.data;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumAlgoDigestConfig;
import eu.easygoit.enums.IEnumSaltGenerator;
import eu.easygoit.enums.IEnumStringOutputType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Digest config dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DigestConfigDto extends AbstractAuditableDto<Long> {

    private String domain;
    private String code;
    //DIGEST ALGORITHMS:   [MD2, MD5, SHA, SHA-256, SHA-384, SHA-512]
    private IEnumAlgoDigestConfig.Types algorithm;
    private Integer iterations;
    private Integer saltSizeBytes;
    private IEnumSaltGenerator.Types saltGenerator;
    private String providerClassName;
    private String providerName;
    private Boolean invertPositionOfSaltInMessageBeforeDigesting;
    private Boolean invertPositionOfPlainSaltInEncryptionResults;
    private Boolean useLenientSaltSizeCheck;
    private Integer poolSize;
    private Boolean unicodeNormalizationIgnored;
    private IEnumStringOutputType.Types stringOutputType;
    private String prefix;
    private String suffix;
}
