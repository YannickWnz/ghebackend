package GHEBACKEND.GHEBACKEND.controller.Versement;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequest;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequestService;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementService;
import GHEBACKEND.GHEBACKEND.service.Versement.Encaissement.EncaissementRequest;
import GHEBACKEND.GHEBACKEND.service.Versement.Encaissement.EncaissementService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/versement")
@RequiredArgsConstructor
public class VersementController {

    private final VersementRequestService service;
    private final EncaissementService encaissementService;

    @PreAuthorize("hasAuthority('VERSEMENT_CREATE')")
    @PostMapping
    public ResponseEntity<?> versement(@RequestBody EncaissementRequest request){
        return ResponseEntity.ok(encaissementService.encaissement(request));
        
    }

    @GetMapping
    public List<Versement> getVersementsByAnneeInscription(@PathParam("annee") int annee){
        return service.getVersementsByAnneeInscription(annee);
    }

    @GetMapping("/current-year")
    public List<Versement> getVersementsByAnneeInscription(){
        return service.getVersementsByAnneeInscription();
    }

    @GetMapping("/between")
    public List<Versement> getVersementsByAnneeInscription(
        @PathParam("startDate") LocalDate startDate, 
        @PathParam("endDate") LocalDate endDate){
        return service.getVersementsByAnneeAcademiqueBetween(startDate,endDate);
    }
}
