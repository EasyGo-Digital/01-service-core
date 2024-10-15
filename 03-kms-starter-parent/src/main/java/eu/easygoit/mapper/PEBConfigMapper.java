package eu.easygoit.mapper;

import eu.easygoit.dto.data.PEBConfigDto;
import eu.easygoit.model.PEBConfig;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Peb config mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface PEBConfigMapper extends EntityMapper<PEBConfig, PEBConfigDto> {

}
