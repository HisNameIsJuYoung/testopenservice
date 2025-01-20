package toy.testopenservice.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.testopenservice.domain.Checklist;
import toy.testopenservice.persistence.ChecklistRepository;

@Service
public class ChecklistService {
    @Autowired
    private ChecklistRepository checklistRepository;
    
    @Transactional(readOnly = true)
    public List<Checklist> getChecklist(String userId) {
        System.out.println(userId);
        return checklistRepository.findByUserId(userId);
    }
    
    @Transactional
    public void putChecklist(int id, String variResu) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Checklist findChecklist = checklistRepository.findById(id).get();
        findChecklist.setVariResu(variResu);
        findChecklist.setCreaDate(timestamp);
    }
}
