package eu.easygoit.mapper;

import eu.easygoit.dto.common.NextCodeDto;
import eu.easygoit.model.AppNextCode;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface App next code mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AppNextCodeMapper extends EntityMapper<AppNextCode, NextCodeDto> {

}
