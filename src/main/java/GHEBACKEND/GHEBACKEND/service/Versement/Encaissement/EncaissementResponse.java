package GHEBACKEND.GHEBACKEND.service.Versement.Encaissement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncaissementResponse {
    private String message;
    private String description;
}
