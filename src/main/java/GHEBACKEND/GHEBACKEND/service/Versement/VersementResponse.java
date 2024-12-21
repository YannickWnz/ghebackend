package GHEBACKEND.GHEBACKEND.service.Versement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VersementResponse {
    private String message;
    private String description;
    private Double montantRestant;
    private Double montantVerse;
    private Double montantTotalVerse;
}
