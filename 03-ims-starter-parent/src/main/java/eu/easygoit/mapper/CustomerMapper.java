package eu.easygoit.mapper;

import eu.easygoit.dto.data.CustomerDto;
import eu.easygoit.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Customer mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface CustomerMapper extends EntityMapper<Customer, CustomerDto> {
}

