package kz.bitlab.StartSpringR.services.impl;

import kz.bitlab.StartSpringR.models.Category;
import kz.bitlab.StartSpringR.repositories.CategoryRepository;
import kz.bitlab.StartSpringR.services.CategoryService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.09.2019
 * @project StartSpringR
 */
@Service("alem")
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findByActiveIsTrueAndId(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByActiveIsTrue();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findByActiveIsTrueAndId(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Category save(Category category) throws ServiceException {
        if (category.getId() == null) {
            categoryRepository.save(category);
        } else {
            throw new ServiceException("Error entity with id " + category.getId() + " already exists");
        }
        return category;
    }


    @Override
    public Category update(Category category) throws ServiceException {
        if (category.getId() != null) {
            categoryRepository.save(category);
        } else {
            throw new ServiceException("Error entity with id " + category.getId() + " not exists");
        }
        return category;
    }
}
