package eu.easygoit.mapper;

import eu.easygoit.dto.data.ChatMessageDto;
import eu.easygoit.model.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Chat message mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ChatMessageMapper extends EntityMapper<ChatMessage, ChatMessageDto> {
}
