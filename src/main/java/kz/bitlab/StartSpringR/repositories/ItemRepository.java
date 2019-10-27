package kz.bitlab.StartSpringR.repositories;

import kz.bitlab.StartSpringR.models.Category;
import kz.bitlab.StartSpringR.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategory_Id(Long id);

}
