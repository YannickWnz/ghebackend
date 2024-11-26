package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.ProfesseurService;
import lombok.RequiredArgsConstructor;

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
     */
}
