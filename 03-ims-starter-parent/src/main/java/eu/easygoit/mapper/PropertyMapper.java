package eu.easygoit.mapper;


import eu.easygoit.dto.data.PropertyDto;
import eu.easygoit.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Property mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface PropertyMapper extends EntityMapper<Property, PropertyDto> {
}
