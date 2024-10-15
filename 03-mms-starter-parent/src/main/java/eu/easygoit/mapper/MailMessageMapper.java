package eu.easygoit.mapper;

import eu.easygoit.dto.data.MailMessageDto;
import eu.easygoit.model.MailMessage;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Mail message mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface MailMessageMapper extends EntityMapper<MailMessage, MailMessageDto> {

}
