package eu.easygoit.mapper;

import eu.easygoit.dto.data.VCalendarEventDto;
import eu.easygoit.model.VCalendarEvent;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface V calendar event mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface VCalendarEventMapper extends EntityMapper<VCalendarEvent, VCalendarEventDto> {

}
