package toy.testopenservice.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.testopenservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByCustomsAndDepartment(String customs, String Department);
    Optional<User> findByUserId(String userId);
}
