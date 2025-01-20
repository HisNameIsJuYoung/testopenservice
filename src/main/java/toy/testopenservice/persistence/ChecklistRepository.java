package toy.testopenservice.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.testopenservice.domain.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {
    List<Checklist> findByCustomsAndDepartment(String customs, String department);
}
