package GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Builder
@Data 
@NoArgsConstructor
@AllArgsConstructor

public class RestePayerResponse{
    private RubriquePayerResponse rubriquePayerResponse;
    private Inscription inscription;
}
