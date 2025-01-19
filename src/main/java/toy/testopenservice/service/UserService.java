package toy.testopenservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.testopenservice.domain.RoleType;
import toy.testopenservice.domain.User;
import toy.testopenservice.persistence.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUser(String userid) {
        User findUser = userRepository.findByUserid(userid).orElseGet(() -> {
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
    public User putUser(User user) {
        System.out.println(user);
        User findUser = userRepository.findByUserid(user.getUserid()).get();
        findUser.setPassword(user.getPassword());
        findUser.setCustoms(user.getCustoms());
        findUser.setDepartment(user.getDepartment());
        System.out.println(findUser);
        return userRepository.save(findUser);
    }
}
