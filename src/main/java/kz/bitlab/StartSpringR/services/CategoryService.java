package kz.bitlab.StartSpringR.services;

import kz.bitlab.StartSpringR.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.09.2019
 * @project StartSpringR
 */
@Service
public interface CategoryService {

    Optional<Category> findById(Long id);

    List<Category> findAll();

    void deleteById(Long id);

    Category save(Category category);

    Category update(Category category);
}
