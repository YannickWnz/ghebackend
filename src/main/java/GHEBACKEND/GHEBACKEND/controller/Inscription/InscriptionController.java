package GHEBACKEND.GHEBACKEND.controller.Inscription;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequest;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequestService;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionResponse;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/inscription")
@RequiredArgsConstructor
public class InscriptionController {
    private final InscriptionRequestService service;

    @PostMapping
    public ResponseEntity<?> createInscription(@RequestBody InscriptionRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.inscrire(request)) ;
        } catch (Exception ex) {
            // TODO: handle exception
            return ResponseEntity.ok(ex);
        }
    }
}
