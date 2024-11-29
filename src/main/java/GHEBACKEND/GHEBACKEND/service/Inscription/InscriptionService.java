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

    public Inscription createInscription(Inscription inscription){
        return inscriptionRepository.save(inscription);
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
                           return Integer.parseInt(Utils.concatCurrentYearAndMonth().toString().concat(Utils.formatString(code.toString())));
            }
            else
                return Integer.parseInt(Utils.formatValueString(Utils.concatCurrentYearAndMonth()).concat(Utils.formatString("1")));
        }catch(Exception ex){
            System.out.println(ex);
                return Integer.parseInt(Utils.formatValueString(Utils.concatCurrentYearAndMonth()).concat(Utils.formatString("1")));
        }
    }

   /*  public Integer concatCurrentYearAndMonth(){
        return Integer.parseInt(String.valueOf(LocalDate.now().getYear()).substring(1,2).concat(LocalDate.now().getMonth().toString()));
    } */
}
