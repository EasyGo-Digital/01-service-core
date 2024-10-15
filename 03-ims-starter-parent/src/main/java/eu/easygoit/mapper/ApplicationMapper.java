package eu.easygoit.mapper;

import eu.easygoit.dto.data.ApplicationDto;
import eu.easygoit.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Application mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ApplicationMapper extends EntityMapper<Application, ApplicationDto> {
}
