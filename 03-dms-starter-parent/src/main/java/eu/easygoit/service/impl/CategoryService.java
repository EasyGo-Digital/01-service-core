package eu.easygoit.service.impl;

import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CrudService;
import eu.easygoit.model.Category;
import eu.easygoit.repository.CategoryRepository;
import eu.easygoit.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Category service.
 */
@Service
@Transactional
@SrvRepo(value = CategoryRepository.class)
public class CategoryService extends CrudService<Long, Category, CategoryRepository> implements ICategoryService {

}
