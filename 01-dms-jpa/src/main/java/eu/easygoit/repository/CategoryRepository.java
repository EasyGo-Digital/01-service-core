package eu.easygoit.repository;


import eu.easygoit.model.Category;

import java.util.Optional;

/**
 * The interface Category repository.
 */
public interface CategoryRepository extends JpaPagingAndSortingRepository<Category, Long> {
    /**
     * Find by name optional.
     *
     * @param categoryName the category name
     * @return the optional
     */
    Optional<Category> findByName(String categoryName);
}
