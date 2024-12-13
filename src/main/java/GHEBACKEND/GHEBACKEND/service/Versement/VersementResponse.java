package GHEBACKEND.GHEBACKEND.service.Versement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VersementResponse {
    private String message;
    private String description;
}
