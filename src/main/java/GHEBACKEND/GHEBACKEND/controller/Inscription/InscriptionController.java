package GHEBACKEND.GHEBACKEND.controller.Inscription;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequest;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequestService;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/inscription")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InscriptionController {
    private final InscriptionRequestService service;
    private final InscriptionService inscriptionService;

    @PostMapping
    public ResponseEntity<?> createInscription(@RequestBody InscriptionRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.inscrire(request)) ;
        } catch (Exception ex) {
            return ResponseEntity.ok(ex);
        }
    }

    @GetMapping
    public ResponseEntity<?> getInscriptions() {
        return ResponseEntity.ok(inscriptionService.getAllInscription());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getInscriptionById(@PathVariable Integer code) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscriptionService.getInscriptionById(code));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<?> updateInscription(
        @PathVariable Integer code,
        @RequestBody InscriptionRequest request){
            try {
                return ResponseEntity.status(HttpStatus.OK)
                    .body(service.modifierInscription(code,request));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteInscription(@PathVariable Integer code){
        try {
            return ResponseEntity.ok(service.supprimerInscription(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
