package GHEBACKEND.GHEBACKEND.controller.Versement;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequest;
import GHEBACKEND.GHEBACKEND.service.Versement.VersementRequestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/versement")
@RequiredArgsConstructor
public class VersementController {

    private final VersementRequestService service;

    @PostMapping
    public ResponseEntity<?> versement(@RequestBody VersementRequest request){
        try {
            return ResponseEntity.ok(service.versement(request));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
