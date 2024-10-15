package GHEBACKEND.GHEBACKEND.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.model.Promotion;
import GHEBACKEND.GHEBACKEND.service.AnneeAcademiqueService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin("http://localhost:3000")
@RestController
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

    @Autowired
    private UtilityMethods utilityMethods;

    @GetMapping("/api/getAnneeAcademique")
    public ResponseEntity<List<AnneeAcademique>> getAllAnneeAcademique() {
        return new ResponseEntity<>(anneeAcademiqueService.getAllAnneeAcademique(), HttpStatus.OK);
    }

    @PostMapping("/api/anneeAcademique")
    public ResponseEntity<?> addAnneAcademique(@RequestBody AnneeAcademique anneeAcademique) {
        
        anneeAcademiqueService.addAnneAcademique(anneeAcademique);
        
        return ResponseEntity.ok("Annee Academique successfully created");
    }
    
    @PutMapping("/api/anneeAcademique/{aacCode}")
    public ResponseEntity<?> updateAnneeAcademique(@PathVariable int aacCode, @RequestBody AnneeAcademique updatedAacData) {

        anneeAcademiqueService.updateAnneeAcademique(
                aacCode, 
                updatedAacData.getAacLib(), 
                updatedAacData.getAacModifierPar(), 
                updatedAacData.getAacStatus()
            );

        return ResponseEntity.ok("Data successfully updated");
    }

    // controller function qui se charge de la suppression
    @DeleteMapping("/api/deleteAnneeAcademique/{aacCode}")
    public ResponseEntity<String> deleteAnneeAcademique(@PathVariable Integer aacCode) {

        try {

            utilityMethods.deleteDonneesRef(aacCode, "T_ANNEE_ACADEMIQUE", "AAC_CODE");

            // promotionService.deletePromoData(proCode);
            return ResponseEntity.ok("Annee Academique delete successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
