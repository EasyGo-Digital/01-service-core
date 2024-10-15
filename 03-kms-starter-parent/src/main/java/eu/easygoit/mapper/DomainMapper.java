package eu.easygoit.mapper;

import eu.easygoit.dto.data.KmsDomainDto;
import eu.easygoit.model.KmsDomain;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Domain mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface DomainMapper extends EntityMapper<KmsDomain, KmsDomainDto> {

}
