package GHEBACKEND.GHEBACKEND.service.Versement;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import GHEBACKEND.GHEBACKEND.repository.Versement.RubriquePayerRepository;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RubriquePayerService {

    private final RubriquePayerRepository rubriquePayerRepository;

    public RubriquePayer createRubriquePayer(RubriquePayer rubriquePayer){
        rubriquePayer.setRbpCode(generatedCode());
        return rubriquePayerRepository.save(rubriquePayer);
    }

    public RubriquePayer getRubriquePayerByCode(@NonNull String code){
        return rubriquePayerRepository
            .findById(code)
            .orElseThrow(
                () -> new IllegalStateException(String.format("Cette rubrique de code %s n'existe pas", code)));
    }

    public List<RubriquePayer> getAllRubriquePayer(){
        return rubriquePayerRepository.findAll();
    }

    public List<RubriquePayer> getRubriquePayerByInscription(Inscription inscription){
        return rubriquePayerRepository
                .findByInscriptionOrderByRbpCodeAsc(inscription)
                .orElseThrow(() -> new IllegalStateException("Aucune rubrique ne correspond à cette inscription"));
    }

    public RubriquePayer updateRubriquePayer(@NonNull String code, RubriquePayer rubriquePayer){
        RubriquePayer existsRubriquePayer = rubriquePayerRepository
            .findById(code)
            .orElseThrow(
                () -> new IllegalStateException(
                    String.format(
                        "Cette rubrique de code %s n'existe pas",
                        code
                    )
                ));

        if(!rubriquePayer.getRbpMontant().isNaN() && 
           !Objects.equals(
                rubriquePayer.getRbpMontant(),
                existsRubriquePayer.getRbpMontant()
        ))
        existsRubriquePayer.setRbpMontant(rubriquePayer.getRbpMontant());

        return rubriquePayerRepository.save(existsRubriquePayer);
    }

    public void deleteRubriquePayer(@NonNull String code){
        Boolean existsById = rubriquePayerRepository.existsById(code);
        if(existsById) rubriquePayerRepository.deleteById(code);
        throw new IllegalStateException("Cette rubrique n'existe pas");
    }

    public RubriquePayer getRubriquePayer(Inscription inscription,RubriqueModel rubrique){
        return rubriquePayerRepository
            .findByInscriptionAndRubriqueOrderByRbpCodeAsc(
                inscription, 
                rubrique);
    }

    public List<RubriquePayer> getRubriquePayerByInscriptionAndClasse
    (
        Inscription inscription,
        ClasseModel classe
    ){
        return rubriquePayerRepository
        .findByInscriptionAndClasseOrderByRubCode(
            inscription.getInsCode(),
            classe.getCla_code());
    }
    
    private String generatedCode(){
        Optional<String> optional = rubriquePayerRepository.findMaxRbpCode();
        try {      
            if(optional.isPresent() && Objects.equals(
                String.valueOf(optional.get()).substring(0,4),
                Utils.concatCurrentYearAndMonth().toString())){

                    String code = optional.get().substring(7,10);
                return Utils.concatCurrentYearAndMonth()
                            .toString()
                            .concat(
                                "RP"+ Utils.formatFoorString(Utils.incrementValue(code).toString()));
            }else
            return Utils.formatValueString(
                Utils.concatCurrentYearAndMonth())
                    .concat("RP"+  Utils.formatFoorString("1"));
        } catch (Exception e) {
            return Utils.formatValueString(
                Utils.concatCurrentYearAndMonth())
                     .concat("RP"+ Utils.formatFoorString("1"));
        }
    }
}
