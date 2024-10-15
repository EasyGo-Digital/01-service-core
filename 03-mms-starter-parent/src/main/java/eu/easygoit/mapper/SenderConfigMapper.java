package eu.easygoit.mapper;

import eu.easygoit.dto.data.SenderConfigDto;
import eu.easygoit.model.SenderConfig;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Sender config mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface SenderConfigMapper extends EntityMapper<SenderConfig, SenderConfigDto> {

}
