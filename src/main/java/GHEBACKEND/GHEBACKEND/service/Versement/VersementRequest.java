package GHEBACKEND.GHEBACKEND.service.Versement;

import java.util.List;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersementRequest {
    private Inscription inscription;
    private Double montantVerse;
}
