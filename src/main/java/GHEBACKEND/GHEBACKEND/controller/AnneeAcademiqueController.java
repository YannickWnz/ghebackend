package GHEBACKEND.GHEBACKEND.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.model.Promotion;
import GHEBACKEND.GHEBACKEND.service.AnneeAcademiqueService;

@CrossOrigin("http://localhost:3000")
@RestController
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

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

}
