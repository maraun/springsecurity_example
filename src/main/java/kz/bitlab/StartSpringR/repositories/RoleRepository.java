package kz.bitlab.StartSpringR.repositories;

import kz.bitlab.StartSpringR.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByIdIn(List<Long> ids);

}
