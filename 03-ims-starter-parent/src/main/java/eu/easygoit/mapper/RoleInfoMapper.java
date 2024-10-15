package eu.easygoit.mapper;

import eu.easygoit.dto.data.RoleInfoDto;
import eu.easygoit.model.RoleInfo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


/**
 * The interface Role info mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface RoleInfoMapper extends EntityMapper<RoleInfo, RoleInfoDto> {

}