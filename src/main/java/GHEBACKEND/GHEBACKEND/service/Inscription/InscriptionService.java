package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.repository.Inscription.InscriptionRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public Inscription 
createInscription(Inscription inscription){
        
        if(!checkInscription(inscription))
            return inscriptionRepository.save(inscription);
        else
            throw new IllegalStateException("Attention, Cette inscription existe déjà");
    }

    /* 
     * Cette méthode retourrne true si cette inscription existe
     */
    private boolean checkInscription(Inscription inscription){
        Integer existsInscription =  getInscription(inscription);
        return inscriptionRepository.existsById(existsInscription);
    }

    public Integer getInscription(Inscription inscription){
        return inscriptionRepository
            .existsInscription(
            inscription.getEtudiant().getEtdCode(),
            inscription.getPromotion().getProCode(),
            inscription.getClasse().getClaCode(),
            inscription.getAnneeAcademique().getAacCode());
    }

    public List<Inscription> getAllInscription(){
        return inscriptionRepository.findAll();
    }

    public Optional<List<Inscription>> getInscriptionByNiveauValidation(@NonNull int insNiveauValidation){
        return Optional
            .ofNullable(inscriptionRepository.findByInsNiveauValidation(insNiveauValidation))
                .orElseThrow(
                    () ->  new IllegalStateException("Aucune inscription à ce niveau...")
                );
    }

    public void deleteInscriptionById(@NonNull Integer code){
        boolean exists = inscriptionRepository.existsById(code);
        if(!exists){
            throw new IllegalStateException("Cette inscription n'existe pas");
        }
        inscriptionRepository.deleteById(code);
    }


    /* 
     * 
     * Cette fonction permet de générer automatiquement le code d'une inscsription
     * @GaiusYan
     */
    public Integer generateInscriptionCode(){
        try{
            Optional<Integer> optional = inscriptionRepository.findMaxInsCode();
            if(optional.isPresent() && 
                Objects.equals(
                    String.valueOf(
                        optional.get()).substring(0,4),
                        Utils.concatCurrentYearAndMonth().toString()) ){

                           Integer code = optional.get();
                           code = Utils.incrementValue(String.valueOf(code).substring(5,9));
                           return Integer.parseInt(
                            Utils.concatCurrentYearAndMonth().toString().concat(Utils.formatString(code.toString())));
            }
            else
                return Integer.parseInt(Utils.formatValueString(
                    Utils.concatCurrentYearAndMonth()).concat(Utils.formatString("1")));
        }catch(Exception ex){
            System.out.println(ex);
                return Integer.parseInt(Utils.formatValueString(Utils.concatCurrentYearAndMonth()).concat(Utils.formatString("1")));
        }
    }

    /* 
     * Modifier une inscription
     */
    public Inscription updateInscription(
        @NonNull Integer code,
        Inscription inscription){
            Inscription existInscription = inscriptionRepository.findById(code)
                .orElseThrow(
                    () -> new IllegalStateException(
                        String.format("Cette inscription de numero %s n'existe pas",code)));

            if(inscription.getEtudiant().getEtdCode() != 0 &&
               !inscription.getEtudiant().getEtdCode().toString().isEmpty() &&
               !Objects.equals(inscription.getEtudiant(),existInscription.getEtudiant()))
            {
                existInscription.setEtudiant(inscription.getEtudiant());
            }

            if(inscription.getAnneeAcademique().getAacCode() != 0 &&
               !Objects.equals(
                inscription.getAnneeAcademique(),
                existInscription.getAnneeAcademique()))
            {
                existInscription.setAnneeAcademique(inscription.getAnneeAcademique());
            }

            if(inscription.getClasse().getClaCode() != 0 &&
            !Objects.equals(
             inscription.getClasse(),
             existInscription.getClasse()))
            {
                existInscription.setClasse(inscription.getClasse());
            }  
            
            if(inscription.getPromotion().getProCode() != 0 &&
            !Objects.equals(
             inscription.getPromotion(),
             existInscription.getPromotion()))
            {
                existInscription.setPromotion(inscription.getPromotion());
            }   

            existInscription.setInsVersion(
                Utils.incrementValue(String.valueOf(inscription.getInsVersion())));

            if(checkInscription(inscription))
                return inscriptionRepository.save(existInscription);
            else 
               throw new IllegalStateException("Attention, Cette inscription existe déjà");
    }
}
