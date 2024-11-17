package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @PostMapping("/api/etudiant")
    public ResponseEntity<String> addNewStudent(@RequestBody EtudiantModel etudiantModel) {

        int code = utilityMethods.studentCodeGenerator();

        EtudiantModel etudiant = new EtudiantModel();

        etudiant.setEtdCode(code);
        etudiant.setEtdNom(etudiantModel.getEtdNom());
        etudiant.setEtdPrenom(etudiantModel.getEtdPrenom());
        etudiant.setEtdPhone(etudiantModel.getEtdPhone());
        etudiant.setEtdSexe(etudiantModel.getEtdSexe());
        etudiant.setEtdVersion(1);
        etudiant.setEtdAddresse(etudiantModel.getEtdAddresse());
        etudiant.setNatCode(etudiantModel.getNatCode());

        etudiantService.addNewStudent(etudiant);

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

        return ResponseEntity.ok("new student successfully added " + code);
    }

}
