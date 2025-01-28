package toy.testopenservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.testopenservice.persistence.UserRepository;

@Service
public class ManagerService {
    @Autowired
    private UserRepository userRepository;

}
