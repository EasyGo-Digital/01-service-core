package eu.easygoit.mapper;

import eu.easygoit.dto.request.RegisterNewAccountDto;
import eu.easygoit.model.RegistredUser;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Register new account mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface RegisterNewAccountMapper extends EntityMapper<RegistredUser, RegisterNewAccountDto> {
}
