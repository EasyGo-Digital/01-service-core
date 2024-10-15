package eu.easygoit.mapper;


import eu.easygoit.dto.data.CategoryDto;
import eu.easygoit.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * The interface Category mapper.
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface CategoryMapper extends EntityMapper<Category, CategoryDto> {
}
