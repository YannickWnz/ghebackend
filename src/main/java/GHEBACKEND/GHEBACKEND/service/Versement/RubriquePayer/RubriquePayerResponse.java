package GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer;

import java.time.LocalDate;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RubriquePayerResponse {
    private Integer code;
    private String designation;
    private LocalDate datePaiement;
    private Double montantPrevu;
    private Double montantRestant;
    private Double montantPayer;
    private boolean isSold;
}
