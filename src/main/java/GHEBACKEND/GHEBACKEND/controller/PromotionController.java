package GHEBACKEND.GHEBACKEND.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.Promotion;
import GHEBACKEND.GHEBACKEND.service.PromotionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    
    @PostMapping("/addPromotion")
    public ResponseEntity<?> addPromotion(@RequestBody Promotion promotion) {

        promotionService.addPromotion(promotion);

        return ResponseEntity.ok("Promotion successfully created");
        // return "Promotion added successfully " + pro_lib;
    }

}
