package eu.easygoit.mapper;

import eu.easygoit.dto.data.DigestConfigDto;
import eu.easygoit.model.DigestConfig;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Digest config mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface DigestConfigMapper extends EntityMapper<DigestConfig, DigestConfigDto> {

}
