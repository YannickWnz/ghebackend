package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentInscriptionDetailsProjection;
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

    public Optional<EtudiantModel> getStudentByCode(Integer code) {
        
        if (code == null) {
            throw new IllegalArgumentException("Invalid student code");
        }
        return etudiantRepo.getStudentByCode(code);
    }

    public Optional<StudentInscriptionDetailsProjection> getStudentInscriptionDetails(Integer code) {

        if(code == null) {
            throw new IllegalArgumentException("Invalid student code");
        }
        return etudiantRepo.getStudentInscriptionDetails(code);

    }

    public void addNewStudent(EtudiantModel etudiantModel) {
        etudiantRepo.save(etudiantModel);
    }

}
