package toy.testopenservice.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.testopenservice.domain.ChecklistResult;

public interface ChecklistResultRepository extends JpaRepository<ChecklistResult, Integer> {
    List<ChecklistResult> findByUserId(String userId);
    ChecklistResult findById(int id);
}
