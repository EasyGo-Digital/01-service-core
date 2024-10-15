package eu.easygoit.mapper;

import eu.easygoit.dto.data.MinAccountDto;
import eu.easygoit.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Min account mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface MinAccountMapper extends EntityMapper<Account, MinAccountDto> {


}
