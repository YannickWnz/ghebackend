package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.lang.StackWalker.Option;
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

    /* 
     * @GaiusYan
     */

    private final InscriptionRepository inscriptionRepository;

    public Inscription createInscription(Inscription inscription){
        
        if(!checkInscription(inscription))
            return inscriptionRepository.save(inscription);
        else
            throw new 
            IllegalStateException("Attention, Cette inscription existe déjà");
    }

/* 
     * Cette méthode retourrne true si cette inscription existe
     */
    private boolean checkInscription(Inscription inscription){
        Integer existsInscription =  getInscription(inscription);
        try {
            return inscriptionRepository.existsById(existsInscription);
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getInscription(Inscription inscription){
        return inscriptionRepository
            .existsInscription(
            inscription.getEtudiant().getEtdCode(),
            inscription.getPromotion().getProCode(),
            inscription.getClasse().getCla_code(),
            inscription.getAnneeAcademique().getAacCode());
    }

    public List<Inscription> getAllInscription(){
        return inscriptionRepository.findAll();
    }

    public Inscription getInscriptionById(
        @NonNull Integer code){
            return inscriptionRepository.findById(code)
            .orElseThrow(
                () -> new IllegalStateException("Ce numéro d'inscription "+ code +" n'existe pas"));
    }

    public void deleteInscriptionById(@NonNull Integer code){
        boolean exists = inscriptionRepository.existsById(code);
        if(!exists){
            throw new IllegalStateException("Ce numéro d'inscription "+ code +" n'existe pas");
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
                    String.valueOf(optional.get()).substring(0,4),
                    Utils.concatCurrentYearAndMonth().toString()) ){

                           Integer code = optional.get();
                           code = Utils.incrementValue(String.valueOf(code)
                            .substring(5,9));
                           return Integer.parseInt(
                            Utils.concatCurrentYearAndMonth().toString()
                            .concat(Utils.formatString(code.toString())));
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
     * @GaiusYan
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

            if(inscription.getClasse().getCla_code() != 0 &&
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
            return inscriptionRepository.save(existInscription);
    }
}