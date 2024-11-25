package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.ProfesseurModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.ProfesseurRepository;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfesseurService {
    private final ProfesseurRepository professeurRepository; 

    public void createProfesseur(ProfesseurModel professeurModel){

    }
}
