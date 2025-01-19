package toy.testopenservice.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSAPublicKeyDTO {
    @Autowired
    private String module;
    private String exponent;
}
