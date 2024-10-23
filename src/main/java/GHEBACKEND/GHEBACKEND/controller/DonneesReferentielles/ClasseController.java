package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.ClasseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    // @GetMapping("/api/classe")
    // public ResponseEntity<List<ClasseModel>> getAllClasse() {

    //     List<ClasseModel> classes = classeService.getAllCLasse();

    //     return ResponseEntity.ok(classes);

    // }
    @GetMapping("/api/classe/totalCount") 
    public Integer getClasseTotalCount() {

        return classeService.getTotalDataNumber("T_CLASSE");

    }

    @GetMapping("/api/classe")
    public ResponseEntity<List<ClasseModel>> getClasse(@RequestParam Integer nivCode, @RequestParam Integer filCode) {

        List<ClasseModel> classes = classeService.getClasseInNiveauAndFiliere(nivCode, filCode);

        return ResponseEntity.ok(classes);

    }

    @PostMapping("/api/classe")
    public ResponseEntity<String> addNewClasse(@RequestBody ClasseModel classeModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            classeService.addNewClasse(classeModel);

            return ResponseEntity.ok("Classe successfully created");

        } catch (Exception e) {
            logger.error("Error while updating classe: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updated classe.");
        }

    }

    @PutMapping("/api/classe/{claCode}")
    public ResponseEntity<String> updateClasseData(@PathVariable Integer claCode, @RequestBody ClasseModel classeModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            classeService.updateClasseData(claCode, classeModel);

            return ResponseEntity.ok("Classe successfully updated");

        } catch (Exception e) {
            logger.error("Error while updating classe: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating classe.");
        }

    }

    @DeleteMapping("/api/classe/{claCode}")
    public ResponseEntity<String> deleteClasse(@PathVariable Integer claCode) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            classeService.deleteClasseData(claCode);

            return ResponseEntity.ok("Classe successfully deleted");

        } catch (Exception e) {
            logger.error("Error while deleting classe: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting classe.");
        }

    }

}
