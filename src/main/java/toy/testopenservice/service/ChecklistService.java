package toy.testopenservice.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.testopenservice.domain.Checklist;
import toy.testopenservice.domain.ChecklistResult;
import toy.testopenservice.dto.ChckRsltDTO;
import toy.testopenservice.persistence.ChecklistRepository;
import toy.testopenservice.persistence.ChecklistResultRepository;

@Service
public class ChecklistService {
    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private ChecklistResultRepository checklistResultRepository;

    @Transactional(readOnly = true)
    public List<ChckRsltDTO> getChecklist(String userId, String customs, String department) {
        List<Checklist> findChecklist = checklistRepository.findByCustomsAndDepartment(customs, department);
        List<ChecklistResult> findChecklistResult = checklistResultRepository.findByUserId(userId);
        List<ChckRsltDTO> resultDTOs = new ArrayList<>();

        for (Checklist checklist : findChecklist) {
            for (ChecklistResult checklistResult : findChecklistResult) {
                ChckRsltDTO chckRsltDTO = new ChckRsltDTO();
                
                if (checklist.getChecListId().equals(checklistResult.getChecListId()) && userId.equals(checklistResult.getUserId())) {
                    chckRsltDTO.setId(checklistResult.getId());
                    chckRsltDTO.setChecListId(checklist.getChecListId());
                    chckRsltDTO.setSwVariId(checklist.getSwVariId());
                    chckRsltDTO.setSystName(checklist.getSystName());
                    chckRsltDTO.setFronVariId(checklist.getFronVariId());
                    chckRsltDTO.setFronVariStep(checklist.getFronVariStep());
                    chckRsltDTO.setUserName(checklistResult.getUserName());
                    chckRsltDTO.setChckRslt(checklistResult.getChckRslt());
                    chckRsltDTO.setCreateDate(checklistResult.getCreateDate());
                    resultDTOs.add(chckRsltDTO);
                }
            }
        }
        return resultDTOs;
    }
    
    @Transactional
    public ChecklistResult putChecklist(int id, String chckRslt) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChecklistResult findChecklistResult = checklistResultRepository.findById(id);
        findChecklistResult.setChckRslt(chckRslt);
        findChecklistResult.setCreateDate(timestamp);
        checklistResultRepository.save(findChecklistResult);
        return checklistResultRepository.findById(id);
    }
}
