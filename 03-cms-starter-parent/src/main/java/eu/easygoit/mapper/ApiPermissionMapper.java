package eu.easygoit.mapper;

import eu.easygoit.dto.data.ApiPermissionDto;
import eu.easygoit.model.ApiPermission;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Api permission mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ApiPermissionMapper extends EntityMapper<ApiPermission, ApiPermissionDto> {
}
