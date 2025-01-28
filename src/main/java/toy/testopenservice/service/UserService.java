package toy.testopenservice.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
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
        return userRepository.findByUserId(userId).orElseGet(User::new);
    }

    @Transactional
    public User insertUser(User user) {
        user.setRole(RoleType.USER);
        return userRepository.save(user);
    }

    @Transactional
    public CompletableFuture<Void> generateChecklistResult(User user) {
        return CompletableFuture.runAsync(() -> {
            List<Checklist> findChecklist = checklistRepository.findChecListIdByCustomsAndDepartment(user.getCustoms(), user.getDepartment());
            for (Checklist checklist : findChecklist) {
                ChecklistResult checklistResult = new ChecklistResult();
                checklistResult.setUserId(user.getUserId());
                checklistResult.setUserName(user.getUserName());
                checklistResult.setChecListId(checklist.getChecListId());
                checklistResultRepository.save(checklistResult);
            }
        });
    }

    @Transactional
    public CompletableFuture<Void> generateDNSChecklist(User user) {
        return CompletableFuture.runAsync(() -> {
            DNSChecklist dNSChecklist = new DNSChecklist();
            dNSChecklist.setUserId(user.getUserId());
            dNSChecklist.setUserName(user.getUserName());
            dNSChecklist.setCustoms(user.getCustoms());
            dNSChecklist.setDepartment(user.getDepartment());
            dNSChecklistRepository.save(dNSChecklist);
        });
    }
    @Transactional
    public User putUser(User findUser, User user) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        findUser.setCustoms(user.getCustoms());
        findUser.setDepartment(user.getDepartment());
        findUser.setCreateDate(timestamp);

        CompletableFuture<Void> checklistFuture = generateChecklistResult(findUser);
        CompletableFuture<Void> dnsChecklistFuture = generateDNSChecklist(findUser);
        CompletableFuture.allOf(checklistFuture, dnsChecklistFuture).join();

        return userRepository.save(findUser);
    }
}
