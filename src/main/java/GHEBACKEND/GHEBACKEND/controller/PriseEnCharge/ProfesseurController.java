package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.ProfesseurModel;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.ProfesseurService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




/* 
 * Controller du professeur 
 * @GaiusYan
 */
@RestController
@RequestMapping("/api/professeur")
/* 
 * @GaiusYan 
 * Cette annotation permet de créer les constructeurs avec tous les arguments nécessaires
 */
@RequiredArgsConstructor
public class ProfesseurController {

    /* 
     * On n'aura pas besoin d'une injection de dépendance pour les services
     * 
     */
    private final ProfesseurService professeurService;

    /* *
     * Post request 
     * @GaiusYan
     */
    @PostMapping
    public ResponseEntity<ProfesseurModel> createProfesseur(@RequestBody ProfesseurModel professeurModel) {
        return ResponseEntity.ok(professeurService.createProfesseur(professeurModel));
    }
    
    @GetMapping
    public ResponseEntity<List<ProfesseurModel>> getAllProfesseur() {
        return ResponseEntity.ok(professeurService.getAllProfesseur());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProfesseurModel>> getProfesseurById(@PathVariable String id) {
        return ResponseEntity.ok(professeurService.getProfesseurByCode(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesseurModel> updateProfesseur(@PathVariable String id, @RequestBody ProfesseurModel professeurModel) {
        return ResponseEntity.ok(professeurService.updateProfesseur(id,professeurModel));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesseur(@PathVariable String id){
        try {
            
            professeurService.deleteProfesseur(id);
            return ResponseEntity.ok("Succès");
        } catch (IllegalStateException ex) {
            // TODO: handle exception
            throw new IllegalStateException("Cette personne n'existe pas");
        }
        
    }
    
}
