package eu.easygoit.mapper;

import eu.easygoit.dto.data.AccountDetailsDto;
import eu.easygoit.model.AccountDetails;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Account details mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AccountDetailsMapper extends EntityMapper<AccountDetails, AccountDetailsDto> {
}
