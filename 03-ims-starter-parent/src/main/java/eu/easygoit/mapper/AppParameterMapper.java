package eu.easygoit.mapper;

import eu.easygoit.dto.data.AppParameterDto;
import eu.easygoit.model.AppParameter;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface App parameter mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AppParameterMapper extends EntityMapper<AppParameter, AppParameterDto> {
}
