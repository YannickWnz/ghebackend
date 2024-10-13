package GHEBACKEND.GHEBACKEND.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.Promotion;
import GHEBACKEND.GHEBACKEND.repository.PromotionRepo;
import GHEBACKEND.GHEBACKEND.service.PromotionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    
    @Autowired
    private PromotionRepo promotionRepo;

    // function s'occupant de la sauvegarde des promotions
    @PostMapping("/api/addPromotion")
    public ResponseEntity<?> addPromotion(@RequestBody Promotion promotion) {

        promotionService.addPromotion(promotion);

        return ResponseEntity.ok("Promotion successfully created");
    }

    // function qui recupere toutes les promotion
    @GetMapping("/api/getAllPromotion")
    public List<Promotion> getAllPromo() {
        return promotionService.getAllPromoRefData();
    }

    // function handle data update
    @PutMapping("/api/promotion/{proCode}")
    public ResponseEntity<Promotion> updatePromoData(@PathVariable int proCode, @RequestBody Promotion updatedPromotionData) {

        Promotion updated = promotionService.updatePromoData(
            proCode,
            updatedPromotionData.getProLib(),
            updatedPromotionData.getModifierPar(),
            updatedPromotionData.getProVersion()
        );

        return ResponseEntity.ok(updated);
    }

}
