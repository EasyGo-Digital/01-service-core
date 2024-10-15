package eu.easygoit.mapper;

import eu.easygoit.dto.request.UpdateAccountRequestDto;
import eu.easygoit.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Account mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AccountMapper extends EntityMapper<Account, UpdateAccountRequestDto> {

}
