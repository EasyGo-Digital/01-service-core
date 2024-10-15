package eu.easygoit.mapper;

import eu.easygoit.dto.data.ConnectionTrackingDto;
import eu.easygoit.model.ConnectionTracking;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


/**
 * The interface Connection tracking mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ConnectionTrackingMapper extends EntityMapper<ConnectionTracking, ConnectionTrackingDto> {

}
