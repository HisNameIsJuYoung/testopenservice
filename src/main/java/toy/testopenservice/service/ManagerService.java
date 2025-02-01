package toy.testopenservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.testopenservice.domain.Checklist;
import toy.testopenservice.domain.ChecklistResult;
import toy.testopenservice.domain.DNSChecklist;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.ManageChckRsltDTO;
import toy.testopenservice.persistence.ChecklistRepository;
import toy.testopenservice.persistence.ChecklistResultRepository;
import toy.testopenservice.persistence.DNSChecklistRepository;
import toy.testopenservice.persistence.UserRepository;

@Service
public class ManagerService {
    @Autowired
    private DNSChecklistRepository dNSChecklistRepository;
    
    @Autowired
    private ChecklistResultRepository checklistResultRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Map<String, Object> getChecklistResult(User user) {
        String customs = user.getCustoms();
        String department = user.getDepartment();
        List<ManageChckRsltDTO> listManagerChckRsltDTO = new ArrayList<>();
        List<User> customsDepartment = userRepository.findByCustomsAndDepartment(customs, department);
        
        for (User cstmDprt : customsDepartment) {
            ManageChckRsltDTO managerChckRsltDTO = new ManageChckRsltDTO();
            int chckAmnt = checklistResultRepository.countByUserId(cstmDprt.getUserId());
            int chckPass = checklistResultRepository.countByUserIdAndChckRslt(cstmDprt.getUserId(), "P");
            int chckFail = checklistResultRepository.countByUserIdAndChckRslt(cstmDprt.getUserId(), "F");
            int chckNthr = checklistResultRepository.countByUserIdAndChckRslt(cstmDprt.getUserId(), "N");
            int unChck = checklistResultRepository.countByUserIdAndChckRslt(cstmDprt.getUserId(), null);
            managerChckRsltDTO.setCustoms(customs);
            managerChckRsltDTO.setDepartment(department);
            managerChckRsltDTO.setUserId(cstmDprt.getUserId());
            managerChckRsltDTO.setUserName(cstmDprt.getUserName());
            managerChckRsltDTO.setChckAmnt(chckAmnt);
            managerChckRsltDTO.setChckPass(chckPass);
            managerChckRsltDTO.setChckFail(chckFail);
            managerChckRsltDTO.setChckNthr(chckNthr);
            managerChckRsltDTO.setUnChck(unChck);
            listManagerChckRsltDTO.add(managerChckRsltDTO);
        }
        List<DNSChecklist> dnsChecklist = dNSChecklistRepository.findByCustomsAndDepartment(customs, department);

        Map<String, Object> result = new HashMap<>();
        result.put("checklist", listManagerChckRsltDTO);
        result.put("dnsChecklist", dnsChecklist);

        return result;
    }

    @Transactional
    public Map<String, Object> getAllResult() {
        List<ChecklistResult> checklistResults = checklistResultRepository.findAll();
        List<Checklist> checklists = checklistRepository.findAll();
        List<DNSChecklist> dnsChecklists = dNSChecklistRepository.findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("checklistResult", checklistResults);
        result.put("checklist", checklists);
        result.put("dnsChecklist", dnsChecklists);

        return result;
    }
}
