package eu.easygoit.mapper;

import eu.easygoit.dto.data.StorageConfigDto;
import eu.easygoit.model.StorageConfig;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Storage config mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface StorageConfigMapper extends EntityMapper<StorageConfig, StorageConfigDto> {

}
