package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("/api/etudiant/{code}")
    public ResponseEntity<?> getStudentByCode(@PathVariable Integer code) {
        
        try {
        
            Optional<EtudiantModel> etudiant = etudiantService.getStudentByCode(code);
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/etudiant")
    public List<EtudiantModel> getAllStudents() {
        
        return etudiantService.getAllStudents();
    }

    @PostMapping("/api/etudiant")
    public ResponseEntity<?> addNewStudent(@RequestBody EtudiantModel etudiantModel) {

        
        try {
            int code = utilityMethods.studentCodeGenerator();
    
            etudiantModel.setEtdCode(code);
            
            etudiantService.addNewStudent(etudiantModel);

            return ResponseEntity.ok(code);

            // return ResponseEntity.ok("Data successfully updated");

        } catch (IllegalArgumentException e) {  
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
