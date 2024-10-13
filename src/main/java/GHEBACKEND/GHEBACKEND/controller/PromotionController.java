package GHEBACKEND.GHEBACKEND.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        // return "Promotion added successfully " + pro_lib;
    }

    // function qui recupere toutes les promotion
    @GetMapping("/api/getAllPromotion")
    public List<Promotion> getAllPromo() {
        return promotionService.getAllPromoRefData();
    }


}
