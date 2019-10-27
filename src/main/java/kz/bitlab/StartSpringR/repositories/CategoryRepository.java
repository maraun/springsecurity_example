package kz.bitlab.StartSpringR.repositories;

import kz.bitlab.StartSpringR.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveIsTrue();

    Optional<Category> findByActiveIsTrueAndId(Long id);
}
