package eu.easygoit.mapper;

import eu.easygoit.dto.data.AnnexDto;
import eu.easygoit.model.Annex;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Annex mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AnnexMapper extends EntityMapper<Annex, AnnexDto> {

}
