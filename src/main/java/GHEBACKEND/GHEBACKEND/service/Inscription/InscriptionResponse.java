package GHEBACKEND.GHEBACKEND.service.Inscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

/* 
 * For any exception 
 * @GaiusYan
 */
public class InscriptionResponse {
    private String description;
    private String message;
}
