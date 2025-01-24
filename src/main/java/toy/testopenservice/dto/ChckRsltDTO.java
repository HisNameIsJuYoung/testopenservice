package toy.testopenservice.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChckRsltDTO {
    private int id;
    private String checListId;
    private String swVariId;
    private String systName;
    private String fronVariId;
    private String fronVariStep;
    private String chckRslt;
    private Timestamp createDate;
}
