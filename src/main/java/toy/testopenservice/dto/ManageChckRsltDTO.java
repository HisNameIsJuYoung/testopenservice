package toy.testopenservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.testopenservice.domain.DNSChecklist;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageChckRsltDTO {
    private String customs;
    private String department;
    private String userId;
    private String userName;
    private int chckAmnt;
    private int chckPass;
    private int chckFail;
    private int chckNthr;
    private int unChck;
}
