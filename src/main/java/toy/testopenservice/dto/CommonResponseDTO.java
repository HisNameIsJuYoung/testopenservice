package toy.testopenservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseDTO {
    private String userid;
    private String username;
    private String role;
    private Object data;
}
