package GHEBACKEND.GHEBACKEND.controller.PriseEnCharge;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("*")
@RequestMapping("api/personnel")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService personnelService;

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<PersonnelModel> postMethodName(@RequestBody PersonnelModel personnelModel) {
=======
    public ResponseEntity<PersonnelModel> createPersonnel(@RequestBody PersonnelModel personnelModel) {
>>>>>>> dev-gaiusYan
        return ResponseEntity.ok(personnelService.createPersonnel(personnelModel));
    }
}
