package GHEBACKEND.GHEBACKEND.controller.Inscription;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentInscriptionDetailsProjection;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequest;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequestService;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("api/inscription")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InscriptionController {
    private final InscriptionRequestService service;
    private final InscriptionService inscriptionService;

    // @PreAuthorize("hasAuthority('INSCRIPTION_CREATE')")
    @PostMapping
    public ResponseEntity<?> createInscription(@RequestBody InscriptionRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.inscrire(request)) ;
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);

        }
    }

    // @PreAuthorize("hasAuthority('INSCRIPTION_READ') and hasRole('ADMINISTRATEUR')")
    @GetMapping
    public ResponseEntity<?> getInscriptions() {
        return ResponseEntity.ok(inscriptionService.getAllInscription());
    }

    @PreAuthorize("hasAuthority('INSCRIPTION_READ')")
    @GetMapping("/{code}")
    public ResponseEntity<?> getInscriptionById(@PathVariable Integer code) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscriptionService.getInscriptionById(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @PreAuthorize("hasAuthority('INSCRIPTION_READ')")
    @GetMapping("/niveau-validation")
    public ResponseEntity<?> getInscriptionByNiveauValidation(@PathParam("niveau") Integer niveau){
        return ResponseEntity.status(HttpStatus.OK).body(service.getInscriptionByNiveauValidation(niveau));
    }

    // Method by Wnz
   /*  @GetMapping("/details/{code}")
    public ResponseEntity<?> getStudentInscriptionDetails(@PathVariable Integer code) {
        try {
        
            List<StudentInscriptionDetailsProjection> data = inscriptionService.getStudentInscriptionDetails(code);
            // Integer data = inscriptionService.getStudentInscriptionDetails(code);

            return ResponseEntity.status(HttpStatus.OK).body(data);
        
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        
        }
    } */
    
    @PreAuthorize("hasAuthority('INSCRIPTION_UPDATE')")
    @PutMapping("/{code}")
    public ResponseEntity<?> updateInscription(
        @PathVariable Integer code,
        @RequestBody InscriptionRequest request){
            return ResponseEntity.status(HttpStatus.OK).body(service.modifierInscription(code,request));
    }

    // @PreAuthorize("hasAuthority('INSCRIPTION_VALIDATE')")
    // @PutMapping("/valider/{code}")
    // public ResponseEntity<?> validerInscription(@PathVariable Integer code) { 
    //     return ResponseEntity.status(HttpStatus.OK).body(service.validerInscription(code));
    // }

    // @PreAuthorize("hasAuthority('INSCRIPTION_REJET')")
    // @PutMapping("/rejeter/{code}")
    // public ResponseEntity<?> rejetterInscription(@PathVariable Integer code) { 
    //     return ResponseEntity.status(HttpStatus.OK).body(service.rejeterInscription(code));
    // }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteInscription(@PathVariable Integer code){
        try {
            return ResponseEntity.ok(service.supprimerInscription(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
