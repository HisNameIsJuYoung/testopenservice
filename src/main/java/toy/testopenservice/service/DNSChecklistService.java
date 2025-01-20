package toy.testopenservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.testopenservice.domain.DNSChecklist;
import toy.testopenservice.persistence.DNSChecklistRepository;

@Service
public class DNSChecklistService {
    @Autowired
    private DNSChecklistRepository dNSChecklistRepository;

    @Transactional(readOnly = true)
    public List<DNSChecklist> getDNSChecklist(String customs, String department) {
        System.out.println(dNSChecklistRepository.findByCustomsAndDepartment(customs, department));
        return dNSChecklistRepository.findByCustomsAndDepartment(customs, department);
    }
}
