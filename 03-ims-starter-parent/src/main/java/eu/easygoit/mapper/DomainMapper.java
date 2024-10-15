package eu.easygoit.mapper;

import eu.easygoit.dto.data.DomainDto;
import eu.easygoit.model.Domain;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Domain mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface DomainMapper extends EntityMapper<Domain, DomainDto> {
}

