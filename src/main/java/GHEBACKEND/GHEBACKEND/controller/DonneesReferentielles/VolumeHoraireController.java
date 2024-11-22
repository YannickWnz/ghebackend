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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseMatiereProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.VolumeHoraire;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.VolumeHoraireRepo;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.VolumeHoraireService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VolumeHoraireController {

    @Autowired
    private VolumeHoraireService volumeHoraireService;

    @Autowired
    private VolumeHoraireRepo volumeHoraireRepo;

    @GetMapping("/api/volumeHoraire/totalCount")
    public Integer getTotalDataNumber() {
        return volumeHoraireService.getTotalDataNumber("T_CLASSE_MATIERE");
    }

    // @GetMapping("/api/volumeHoraire")
    // public List<VolumeHoraire> getAllVolumeHoraire() {
    //     return volumeHoraireService.getAllVolumeHoraires();
    // }

    @GetMapping("/api/volumeHoraire/data")
    public ResponseEntity<List<ClasseMatiereProjection>> getVolumeHoraireData() {

        // List<ClasseModel> classes = classeService.getClasseInNiveauAndFiliere(nivCode, filCode);
        List<ClasseMatiereProjection> volumeHoraireData = volumeHoraireService.getVolumeHoraireata();

        return ResponseEntity.ok(volumeHoraireData);

    }

    @GetMapping("/api/volumeHoraire/getAll")
    public ResponseEntity<List<VolumeHoraire>> getClasse() {

        // List<ClasseModel> classes = classeService.getClasseInNiveauAndFiliere(nivCode, filCode);
        List<VolumeHoraire> volumeHoraireList = volumeHoraireRepo.findAll();

        return ResponseEntity.ok(volumeHoraireList);

    }

    @GetMapping("/api/volumeHoraire")
    public ResponseEntity<List<VolumeHoraire>> getClasse(@RequestParam Integer claCode, @RequestParam Integer matCode) {

        // List<ClasseModel> classes = classeService.getClasseInNiveauAndFiliere(nivCode, filCode);
        List<VolumeHoraire> assignedVolumeHoraire = volumeHoraireService.getAssignedVolumeHoraire(claCode, matCode);

        return ResponseEntity.ok(assignedVolumeHoraire);

    }

    @PostMapping("/api/volumeHoraire")
    public ResponseEntity<String> addNewVolumeHoraire(@RequestBody VolumeHoraire volumeHoraire) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            // validation du libelle en utilisant la methode definie dans la classe utilityMethods
            // if(!UtilityMethods.validateInputString(rubriqueModel.getRubLib(), 4, 100)) {
            //     return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            // }

            // run service function addNewNiveau if no error from validation
            volumeHoraireService.addNewVolumeHoraire(volumeHoraire);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("New Volume Horaire successfully added");

        } catch (Exception e) {
            logger.error("Error while creating data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating volume horaire data.");
        }
        
    }

    
    @PutMapping("/api/volumeHoraire/{code}")
    public ResponseEntity<String> updateVolumeHoraire(@PathVariable Integer code, @RequestBody VolumeHoraire volumeHoraire) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            // run service function addNewNiveau if no error from validation
            volumeHoraireService.updateVolumeHoraireData(code, volumeHoraire);
    
            // return Response with statusCode 200
            return ResponseEntity.ok("Data successfully updated");

        } catch (Exception e) {
            logger.error("Error while updating volume horaire: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating volume horaire data.");
        }


    }

        
    @DeleteMapping("/api/volumeHoraire/{code}")
    public ResponseEntity<String> deleteVolumeHoraire(@PathVariable Integer code) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            volumeHoraireService.deleteVolumeHoraireData(code);

            return ResponseEntity.ok("Data successfully deleted");

        } catch (Exception e) {
            logger.error("Error while deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting data.");
        }

    }



}
