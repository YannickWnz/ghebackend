package GHEBACKEND.GHEBACKEND.service.Inscription;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentInscriptionDetailsProjection;
import GHEBACKEND.GHEBACKEND.repository.Inscription.InscriptionRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import io.micrometer.common.lang.NonNull;
import jakarta.transaction.Transactional;
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
            System.out.println(e);
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

    public List<Inscription> getInscriptionByNiveauValidation(@NonNull Integer niveauValidation){
       if(niveauValidation == null) throw new RuntimeException("Attention, le niveau de validation est null");
        return inscriptionRepository
                .findByInsNiveauValidationOrderByInsCodeAsc(niveauValidation)
                .orElseThrow(() -> new IllegalStateException(
                    String.format(
                        "Aucune donnée au niveau %s",
                        niveauValidation)
                    )
                );
    }

    public Inscription getInscriptionById(
        @NonNull Integer code){
            return inscriptionRepository.findById(code)
            .orElseThrow(
                () -> new IllegalStateException("L'inscription de numéro "+ code +" n'existe pas"));
    }

    public void deleteInscriptionById(@NonNull Integer code){
        boolean exists = inscriptionRepository.existsById(code);
        if(!exists){
            throw new IllegalStateException("Ce numéro d'inscription "+ code +" n'existe pas");
        }else{
            Inscription inscription = getInscriptionById(code);
            //Vérifier le niveau de validation de cette inscription avant la suppression
            if(getMaximumNiveauValidation(inscription))
                throw new IllegalStateException(
                    String.format(
                        "L'inscription numéro %s est déjà validée aucune suppression n'est possible",
                        inscription.getInsCode()));
            else inscriptionRepository.deleteById(code);
            
        }
    }

    public boolean getMaximumNiveauValidation(Inscription inscription){
        return inscription.getInsNiveauValidation() >= 3 ;
    }

    public boolean getMinimunNiveauValidation(Inscription inscription){
        return inscription.getInsNiveauValidation() > 0 ;
    }

    public boolean getMaximumAndMinimunNiveauValidation(Inscription inscription){
        return inscription.getInsNiveauValidation() < 3 && inscription.getInsNiveauValidation() >= 0;
    }

    /* 
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
    @Transactional
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

    
    // public Integer getStudentInscriptionDetails(Integer code) {
    public List<StudentInscriptionDetailsProjection> getStudentInscriptionDetails(Integer code) {

        if(code == null) {
            throw new IllegalArgumentException("Invalid student code");
        }
        return inscriptionRepository.getStudentInscriptionDetails(code);

    }

    public Inscription validateInscription(Integer code){
        //Vérifier si cette inscription
        Inscription existInscription = getInscriptionById(code);
        //Vérifier si l'inscription le niveau de cette inscription n'a pas encore atteint le maximum
        if(getMaximumAndMinimunNiveauValidation(existInscription)) 
            existInscription.setInsNiveauValidation(Utils.incrementValue(String.valueOf(existInscription.getInsNiveauValidation()))); 
        else throw new RuntimeException("Cette inscription est déjà validée...");
        return inscriptionRepository.save(existInscription);
    }

    public Inscription rejeterInscription(Integer code){
        Inscription existInscription = getInscriptionById(code);
        //Vérifier si l'inscription le niveau de cette inscription n'a pas encore atteint le maximum
        if(getMinimunNiveauValidation(existInscription))
            existInscription.setInsNiveauValidation(Utils.decrementValue(String.valueOf(existInscription.getInsNiveauValidation()))); 
        else throw new RuntimeException("Cette inscription ne peut plus être rejetée...");
        return inscriptionRepository.save(existInscription);
    }
}