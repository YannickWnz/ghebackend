package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

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

    public void addNewStudent(EtudiantModel etudiantModel) {

        etudiantRepo.save(etudiantModel);

    }

}
