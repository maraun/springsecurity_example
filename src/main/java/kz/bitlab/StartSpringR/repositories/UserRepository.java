package kz.bitlab.StartSpringR.repositories;

import kz.bitlab.StartSpringR.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Assylkhan
 * on 6.09.2019
 * @project StartSpringR
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getOneByUsername(String username);

}
