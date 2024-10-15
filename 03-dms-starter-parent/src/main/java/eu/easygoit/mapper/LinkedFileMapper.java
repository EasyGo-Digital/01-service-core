package eu.easygoit.mapper;


import eu.easygoit.dto.common.LinkedFileRequestDto;
import eu.easygoit.model.LinkedFile;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Linked file mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface LinkedFileMapper extends EntityMapper<LinkedFile, LinkedFileRequestDto> {
}
