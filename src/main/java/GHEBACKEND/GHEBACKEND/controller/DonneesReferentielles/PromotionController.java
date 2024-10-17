package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Promotion;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.PromotionRepo;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.PromotionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    
    @Autowired
    private PromotionRepo promotionRepo;

    // function s'occupant de la sauvegarde des promotions
    @PostMapping("/api/promotion")
    public ResponseEntity<?> addPromotion(@RequestBody Promotion promotion) {

        promotionService.addPromotion(promotion);

        return ResponseEntity.ok("Promotion successfully created");
    }

    @PostMapping("/api/test")
    public Promotion addTestPromotion(@RequestBody Promotion promotion) {

        return promotion;
    }

    // function qui recupere toutes les promotions
    @GetMapping("/api/promotion")
    public List<Promotion> getAllPromo() {
        return promotionService.getAllPromoRefData();
    }

    // function qui se charge de la mise a jour
    @PutMapping("/api/promotion/{proCode}")
    public ResponseEntity<Promotion> updatePromoData(@PathVariable int proCode, @RequestBody Promotion updatedPromotionData) {

        Promotion updated = promotionService.updatePromoData(
            proCode,
            updatedPromotionData.getProLib(),
            updatedPromotionData.getModifierPar()
        );

        return ResponseEntity.ok(updated);
    }

    // controller function qui se charge de la suppression
    @DeleteMapping("/api/promotion/{proCode}")
    public ResponseEntity<String> deletePromoData(@PathVariable Integer proCode) {

        try {

            promotionService.deletePromoData(proCode);
            return ResponseEntity.ok("Promotion delete successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
