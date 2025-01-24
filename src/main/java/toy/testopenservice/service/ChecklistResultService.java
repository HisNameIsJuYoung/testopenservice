package toy.testopenservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import toy.testopenservice.domain.ChecklistResult;
import toy.testopenservice.persistence.ChecklistResultRepository;

public class ChecklistResultService {
    @Autowired
    private ChecklistResultRepository checklistResultRepository;

    @Transactional(readOnly = true)
    public ChecklistResult getChecklistResultByUserId(String userId) {
        List<ChecklistResult> results = checklistResultRepository.findByUserId(userId);
        if (results.isEmpty()) {
            return null; // or handle the empty case as needed
        }
        return results.get(0);
    }
}
