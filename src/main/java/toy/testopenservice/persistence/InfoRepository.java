package toy.testopenservice.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import toy.testopenservice.domain.Info;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    @Query("SELECT new toy.testopenservice.domain.Info(i.id, i.title, i.content, i.createDate) FROM Info i")
    List<Info> findIdTitleContentCreateDate();
}
