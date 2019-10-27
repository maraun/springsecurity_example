package kz.bitlab.StartSpringR.services.impl;

import kz.bitlab.StartSpringR.models.Category;
import kz.bitlab.StartSpringR.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.09.2019
 * @project StartSpringR
 */
@Service("salem")
public class CategoryService2Impl implements CategoryService {
    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }
}
