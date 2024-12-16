package GHEBACKEND.GHEBACKEND.service.Versement;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.ClasseRepo;
import GHEBACKEND.GHEBACKEND.repository.Versement.VersementRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersementService {

    private final VersementRepository versementRepository;

    public Versement createVersement(Versement versement){
        versement.setVerCode(generatedCode());
        versement.setVerDate(LocalDate.now());
        return versementRepository.save(versement);
    }

    public List<Versement> getAllVersement(){
        return versementRepository.findAll();
    }

    public List<Versement> getVersementByInscription(Inscription inscription){
        return versementRepository.findByInscriptionOrderByVerDateAsc(inscription)
                .orElseThrow(
                    () -> new IllegalStateException(String.format("Aucun versement pour l'inscription de numero %s", 
                    inscription.getInsCode())));
    }

    public List<Versement> getVersementsByAnneeInscription(){
        int currentYear = LocalDate.now().getYear();
        return versementRepository.findVersementsByAnneeInscription(currentYear);
    }

    public List<Versement> getVersementsByAnneeInscription(int currentYear){
        return versementRepository.findVersementsByAnneeInscription(currentYear);
    }

    public List<Versement> getVersementsByAnneeAcademiqueBetween(LocalDate startDate,LocalDate endDate){
        return versementRepository.findByVerDateBetween(startDate, endDate);
    }


     private String generatedCode(){
        Optional<String> optional = versementRepository.findMaxVerCode();
        try {      
            if(optional.isPresent() && Objects.equals(
                String.valueOf(optional.get()).substring(0,4),
                Utils.concatCurrentYearAndMonth().toString())){
                    String code = optional.get().substring(6,9);
                return Utils.concatCurrentYearAndMonth().toString()
                .concat("V"+ Utils.formatFoorString(Utils.incrementValue(code).toString()));
            }else
            return Utils.formatValueString(
                Utils.concatCurrentYearAndMonth()).concat("V"+  Utils.formatFoorString("1"));
        } catch (Exception e) {
            return Utils.formatValueString(
                Utils.concatCurrentYearAndMonth()).concat("V"+ Utils.formatFoorString("1"));
        }
    } 
}
