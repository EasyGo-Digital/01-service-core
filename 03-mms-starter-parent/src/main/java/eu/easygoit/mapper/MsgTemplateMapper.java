package eu.easygoit.mapper;

import eu.easygoit.dto.data.MsgTemplateDto;
import eu.easygoit.model.MsgTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Template mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface MsgTemplateMapper extends EntityMapper<MsgTemplate, MsgTemplateDto> {
}
