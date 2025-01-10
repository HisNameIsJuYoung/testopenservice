package toy.testopenservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.testopenservice.domain.Info;
import toy.testopenservice.persistence.InfoRepository;

@Service
public class InfoService {
    @Autowired
    private InfoRepository infoRepository;

    @Transactional
    public void postInfo(Info info) {
        info.setCount(0);
        infoRepository.save(info);
    }

    @Transactional(readOnly = true)
    public List<Info> getInfo() {
        return infoRepository.findIdTitleContentCreateDate();
    }
    
    @Transactional
    public void deleteInfo(int id) {
        infoRepository.deleteById(id);
    }

    @Transactional
    public void putInfo(Info info) {
        Info findInfo = infoRepository.findById(info.getId()).get();
        findInfo.setTitle(info.getTitle());
        findInfo.setContent(info.getContent());
    }
}
