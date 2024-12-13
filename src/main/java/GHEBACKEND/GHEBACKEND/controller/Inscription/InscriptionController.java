package GHEBACKEND.GHEBACKEND.controller.Inscription;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequest;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequestService;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionService;
import jakarta.websocket.server.PathParam;
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @PreAuthorize("hasAuthority('INSCRIPTION_READ')")
    @GetMapping
    public ResponseEntity<?> getInscriptions() {
        return ResponseEntity.ok(inscriptionService.getAllInscription());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getInscriptionById(@PathVariable Integer code) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscriptionService.getInscriptionById(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/niveau-validation")
    public ResponseEntity<?> getInscriptionByNiveauValidation(@PathParam("niveau") Integer niveau){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscriptionService.getInscriptionByNiveauValidation(niveau));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
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

    @PutMapping("/valider/{code}")
    public ResponseEntity<?> validerInscription(@PathVariable Integer code) { 
        return ResponseEntity.status(HttpStatus.OK).body(inscriptionService.validateInscription(code));
    }

    @PutMapping("/rejeter/{code}")
    public ResponseEntity<?> rejetterInscription(@PathVariable Integer code) { 
        return ResponseEntity.status(HttpStatus.OK).body(inscriptionService.rejeterInscription(code));
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
