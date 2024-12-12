package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonneContactModel;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentContactDataProjection;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonneContactRepo;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.PersonneContactService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin("http://localhost:3000")
@RestController
public class PersonneContactController {

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private PersonneContactService personneContactService;

    @Autowired
    private PersonneContactRepo personneContactRepo;
    
    @GetMapping("api/personneContact/{studentCode}")
    private ResponseEntity<?> getStudentContacts(@PathVariable Integer studentCode) {
        try {

            List<StudentContactDataProjection> studentContacts = personneContactService.getStudentContacts(studentCode);

            return ResponseEntity.ok(studentContacts);

        } catch (IllegalArgumentException e) {  
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("api/personneContact/{studentCode}")
    private ResponseEntity<String> addNewContacts(@PathVariable Integer studentCode, @RequestBody List<PersonneContactModel> personneContactModel) {


        for(PersonneContactModel contact : personneContactModel) {
            System.out.println(contact.getConPrenom());
            // PersonneContactModel personneContactModel = new PersonneContactModel();

            Integer code = utilityMethods.codeGenerator("CON_CODE", "T_PERSONNE_CONTACT");

            contact.seConCode(code);
            contact.setConAddresse(contact.getConAddresse());
            contact.setConEmail(contact.getConEmail());
            contact.setConNom(contact.getConNom());
            contact.setConPrenom(contact.getConPrenom());
            contact.setConVersion(1);
            contact.setLieCode(contact.getLieCode());
            contact.setEtdCode(studentCode);
            // contact.setConCreerPar(contact.getConCreerPar());

            personneContactRepo.save(contact);

        }

        return ResponseEntity.ok("Success");

    }

}
