package GHEBACKEND.GHEBACKEND.service.Versement.Encaissement;

import java.sql.Date;
import java.time.LocalDate;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncaissementRequest {
    private EtudiantModel etudiant;
    private LocalDate date;
    private Double montant;
    private Inscription inscription;
}
