package kz.bitlab.StartSpringR.repositories;

import kz.bitlab.StartSpringR.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
