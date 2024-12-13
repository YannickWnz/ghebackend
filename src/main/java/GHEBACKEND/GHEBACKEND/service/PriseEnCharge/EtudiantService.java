package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.EtudiantRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;


@Service
public class EtudiantService {

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private EtudiantRepo etudiantRepo;

    public List<EtudiantModel> getAllStudents() {

        return etudiantRepo.findAll();

    }

    public void addNewStudent(EtudiantModel etudiantModel) {
        etudiantRepo.save(etudiantModel);
    }

    public EtudiantModel getEtudiantByEtuCode(Integer code){
        return etudiantRepo
            .findById(code)
            .orElseThrow(() -> new IllegalStateException(String.format("Le numéro %s n'existe pas", code)));
    }

}
