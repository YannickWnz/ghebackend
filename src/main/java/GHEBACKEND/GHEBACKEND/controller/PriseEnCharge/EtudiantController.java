package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonneContactRepo;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.EtudiantService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;


@CrossOrigin("http://localhost:3000")
@RestController
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private PersonneContactRepo personneContactRepo;


    @GetMapping("/api/etudiant")
    public List<EtudiantModel> getAllStudents() {
        
        return etudiantService.getAllStudents();
    }

    @PostMapping("/api/etudiant")
    public ResponseEntity<Integer> addNewStudent(@RequestBody EtudiantModel etudiantModel) {

        int code = utilityMethods.studentCodeGenerator();

        etudiantModel.setEtdCode(code);

        // System.out.println(etudiantModel.getEtdCreerPar());

        etudiantService.addNewStudent(etudiantModel);

        // for(PersonneContactModel contact : etudiantModel.getContacts()) {
        //     System.out.println(contact.getConPrenom());
        //     PersonneContactModel personneContactModel = new PersonneContactModel();

        //     personneContactModel.seConCode(1001);
        //     personneContactModel.setConAddresse(contact.getConAddresse());
        //     personneContactModel.setConEmail(contact.getConEmail());
        //     personneContactModel.setConNom(contact.getConNom());
        //     personneContactModel.setConPrenom(contact.getConPrenom());
        //     personneContactModel.setConVersion(1);
        //     personneContactModel.setLieCode(code);

        //     personneContactRepo.save(personneContactModel);

        // }

        return ResponseEntity.ok(code);
    }

}
