package toy.testopenservice.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import toy.testopenservice.domain.ChecklistResult;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ChecklistResultRepositoryTest {

    @Autowired
    private ChecklistResultRepository checklistResultRepository;

    // @Test
    // public void testCountByUserId() {
    //     // Given
    //     String userId = "081539";
    //     ChecklistResult checklistResult = new ChecklistResult();
    //     checklistResult.setUserId(userId);
    //     checklistResultRepository.save(checklistResult);

    //     // When
    //     int count = checklistResultRepository.countByUserId(userId);

    //     // Then
    //     assertThat(count).isEqualTo(1);
    // }

    @Test
    public void testFindByUserId() {
        // Given
        String userId = "081539";
        ChecklistResult checklistResult = new ChecklistResult();
        checklistResult.setUserId(userId);
        checklistResultRepository.save(checklistResult);

        // When
        List<ChecklistResult> results = checklistResultRepository.findByUserId(userId);

        // Then
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getUserId()).isEqualTo(userId);
    }

    // @Test
    // public void testFindById() {
    //     // Given
    //     ChecklistResult checklistResult = new ChecklistResult();
    //     checklistResult = checklistResultRepository.save(checklistResult);
    //     int id = checklistResult.getId();

    //     // When
    //     ChecklistResult foundResult = checklistResultRepository.findById(id);

    //     // Then
    //     assertThat(foundResult).isNotNull();
    //     assertThat(foundResult.getId()).isEqualTo(id);
    // }
}