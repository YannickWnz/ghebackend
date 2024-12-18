package GHEBACKEND.GHEBACKEND.controller.Versement;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer.RestePayerResponse;
import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer.RubriquePayerRequest;
import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer.RubriquePayerRequestService;
import GHEBACKEND.GHEBACKEND.service.Versement.RubriquePayer.RubriquePayerResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rubriques")
@RequiredArgsConstructor
public class RubriquePayerController {

    private final RubriquePayerRequestService service;

    @GetMapping
    public ResponseEntity<List<RubriquePayerResponse>> getRubriqueByInscription(@PathParam("inscriptionId") Integer inscriptionId){
        return ResponseEntity.ok(service.getRubriquesByInscription(inscriptionId));
    }

    @GetMapping("reste-a-payer")
    public ResponseEntity<List<RestePayerResponse>> getRubriqueByEtudiant(@PathParam("etudiantId") Integer etudiantId){
        return ResponseEntity.ok(service.afficherRubriqueNonSolde(etudiantId));
    }
}
