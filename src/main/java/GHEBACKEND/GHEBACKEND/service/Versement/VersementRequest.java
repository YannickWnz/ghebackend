package GHEBACKEND.GHEBACKEND.service.Versement;

import java.time.LocalDate;
import java.util.List;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersementRequest {
    private Inscription inscription;
    private LocalDate date;
    @NonNull
    private Double montantVerse;
}
