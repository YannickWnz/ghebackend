package GHEBACKEND.GHEBACKEND.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.service.AnneeAcademiqueService;

@CrossOrigin("http://localhost:3000")
@RestController
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

    @GetMapping("/api/getAnneeAcademique")
    public ResponseEntity<List<AnneeAcademique>> getAllAnneeAcademique() {
        return new ResponseEntity<>(anneeAcademiqueService.getAllAnneeAcademique(), HttpStatus.OK);
    }

}
