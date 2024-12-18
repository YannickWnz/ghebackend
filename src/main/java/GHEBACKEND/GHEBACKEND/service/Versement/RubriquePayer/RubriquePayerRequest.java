package GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer;

import GHEBACKEND.GHEBACKEND.controller.PriseEnCharge.EtudiantController;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RubriquePayerRequest {
    private Inscription inscription;
}
