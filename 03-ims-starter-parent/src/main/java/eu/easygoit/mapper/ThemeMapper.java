package eu.easygoit.mapper;

import eu.easygoit.dto.data.ThemeDto;
import eu.easygoit.model.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Theme mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ThemeMapper extends EntityMapper<Theme, ThemeDto> {
}
