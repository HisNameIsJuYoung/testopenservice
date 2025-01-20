package toy.testopenservice.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.testopenservice.domain.DNSChecklist;

public interface DNSChecklistRepository extends JpaRepository<DNSChecklist, Integer> {
    List<DNSChecklist> findByCustomsAndDepartment(String customs, String department);
}
