package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.Exception.CustomException;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin("*")
@RequestMapping("api/personnel")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService personnelService;

    @PostMapping
    public ResponseEntity<?> createPersonnel(@RequestBody PersonnelModel personnelModel) {
        try {
            return ResponseEntity.ok(personnelService.createPersonnel(personnelModel));
            
        } catch (IllegalStateException e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
 
    @GetMapping
    public ResponseEntity<List<PersonnelModel>> getAllPersonnel() {
        return ResponseEntity.ok(personnelService.getAllPersonnel());
    }

    @GetMapping("/{code}")
    public ResponseEntity<PersonnelModel> getPersonnelById(@PathVariable Integer code){
        return ResponseEntity.ok(personnelService.getPersonnelById(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> updatePersonnel(@PathVariable Integer code, @RequestBody PersonnelModel personnelModel) {
        try {
            return ResponseEntity.ok(personnelService.updatePersonnel(code,personnelModel));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deletePersonnel(@PathVariable Integer code){
        try{
            personnelService.deletePersonnel(code);
            return ResponseEntity.status(HttpStatus.OK).body("Succès");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur survenue lors de la suppression");
        }
    }
}
