package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin("*")
@RequestMapping("api/personnel")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService personnelService;

    @PostMapping
    public ResponseEntity<PersonnelModel> createPersonnel(@RequestBody PersonnelModel personnelModel) {
        return ResponseEntity.ok(personnelService.createPersonnel(personnelModel));
    }
 
    @GetMapping
    public ResponseEntity<List<PersonnelModel>> getAllPersonnel() {
        return ResponseEntity.ok(personnelService.getAllPersonnel());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Optional<PersonnelModel>> getPersonnelById(@PathVariable Integer code){
        return ResponseEntity.ok(personnelService.getPersonnelById(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<PersonnelModel> updatePersonnel(@PathVariable Integer code, @RequestBody PersonnelModel personnelModel) {
        //TODO: process PUT request
        return ResponseEntity.ok(personnelService.updatePersonnel(code,personnelModel));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deletePersonnel(@PathVariable Integer code){
        personnelService.deletePersonnel(code);
        return ResponseEntity.ok("Suppression effectuée");
    }
}
