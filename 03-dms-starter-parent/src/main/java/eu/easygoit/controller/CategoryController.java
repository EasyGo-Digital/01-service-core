package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlDef;
import eu.easygoit.com.rest.controller.impl.MappedCrudController;
import eu.easygoit.dto.data.CategoryDto;
import eu.easygoit.exception.handler.DmsExceptionHandler;
import eu.easygoit.mapper.CategoryMapper;
import eu.easygoit.model.Category;
import eu.easygoit.service.impl.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Category controller.
 */
@Slf4j
@Validated
@RestController
@CtrlDef(handler = DmsExceptionHandler.class, mapper = CategoryMapper.class, minMapper = CategoryMapper.class, service = CategoryService.class)
@RequestMapping(path = "/api/v1/private/category")
public class CategoryController extends MappedCrudController<Long, Category, CategoryDto, CategoryDto, CategoryService> {

}
