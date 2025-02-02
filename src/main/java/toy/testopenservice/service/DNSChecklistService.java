package toy.testopenservice.service;

import java.sql.Timestamp;
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
        return dNSChecklistRepository.findByCustomsAndDepartment(customs, department);
    }

    @Transactional
    public void putDNSChecklist(DNSChecklist dNSChecklist) {
        System.out.println("dNSChecklist = " + dNSChecklist);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DNSChecklist findDNSChecklist = dNSChecklistRepository.findById(dNSChecklist.getId()).get();
        findDNSChecklist.setDnsChckRslt(dNSChecklist.getDnsChckRslt());
        if (dNSChecklist.getDnsChckRslt() != null) {
            findDNSChecklist.setCretDate(timestamp);
        } else {
            findDNSChecklist.setCretDate(null);
        }
        dNSChecklistRepository.save(findDNSChecklist);
    }

    @Transactional(readOnly = true)
    public DNSChecklist getDNSChecklistById(int id) {
        return dNSChecklistRepository.findById(id).get();
    }
}
