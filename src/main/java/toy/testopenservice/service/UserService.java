package toy.testopenservice.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.testopenservice.domain.Checklist;
import toy.testopenservice.domain.ChecklistResult;
import toy.testopenservice.domain.DNSChecklist;
import toy.testopenservice.domain.RoleType;
import toy.testopenservice.domain.User;
import toy.testopenservice.persistence.ChecklistRepository;
import toy.testopenservice.persistence.ChecklistResultRepository;
import toy.testopenservice.persistence.DNSChecklistRepository;
import toy.testopenservice.persistence.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private DNSChecklistRepository dNSChecklistRepository;

    @Autowired
    private ChecklistResultRepository checklistResultRepository;

    @Transactional(readOnly = true)
    public User getUser(String userId) {
        User findUser = userRepository.findByUserId(userId).orElseGet(() -> {
                return new User();
            });
        return findUser;
    }

    @Transactional
    public User insertUser(User user) {
        user.setRole(RoleType.USER);
        return userRepository.save(user);
    }

    @Transactional
    public void generateChecklistResult(User user) {
        List<Checklist> findChecklist = checklistRepository.findChecListIdByCustomsAndDepartment(user.getCustoms(), user.getDepartment());
        
        for (Checklist checklist : findChecklist) {
            ChecklistResult checklistResult = new ChecklistResult();
            checklistResult.setUserId(user.getUserId());
            checklistResult.setUserName(user.getUserName());
            checklistResult.setChecListId(checklist.getChecListId());
            checklistResultRepository.save(checklistResult);
        }
    }

    @Transactional
    public void generateDNSChecklist(User user) {
        DNSChecklist dNSChecklist = new DNSChecklist();
        dNSChecklist.setUserId(user.getUserId());
        dNSChecklist.setUserName(user.getUserName());
        dNSChecklist.setCustoms(user.getCustoms());
        dNSChecklist.setDepartment(user.getDepartment());
        dNSChecklistRepository.save(dNSChecklist);
    }

    @Transactional
    public User putUser(User user) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User findUser = userRepository.findByUserId(user.getUserId()).get();
        findUser.setPassword(user.getPassword());
        findUser.setCustoms(user.getCustoms());
        findUser.setDepartment(user.getDepartment());
        findUser.setCreateDate(timestamp);
        this.generateChecklistResult(user);
        this.generateDNSChecklist(findUser);
        return userRepository.save(findUser);
    }
}
