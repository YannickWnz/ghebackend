package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.LienModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.LienService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LienController {

    @Autowired
    private LienService lienService;

    @Autowired
    private UtilityMethods utilityMethods;
    
    @GetMapping("/api/lien")
    public List<LienModel> getAllLienData() {
        return lienService.getAllLienData();
    }

    @GetMapping("/api/lien/totalCount")
    public Integer getLienTotalCount() {
        return lienService.getTotalDataNumber("T_LIEN");
    }

    @GetMapping("/api/lien/lienLib/{code}")
    public ResponseEntity<String> fetchLienLibUsingLienCode(@PathVariable Integer code) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            // if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
            //     return new ResponseEntity<>("Invalid Lien Code format", HttpStatus.BAD_REQUEST);
            // }

            String lienLib = lienService.fetchLienLibUsingLienCode(code);

            return ResponseEntity.ok(lienLib);
            
        } catch (Exception e) {
            logger.error("Error while creating lien: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating lien.");
        }

    }

    @PostMapping("/api/lien")
    public ResponseEntity<String> addNewLien(@RequestBody LienModel lienModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

         
            // if(!UtilityMethods.validateInputString(lienModel.getLieLib(), 2, 255)) {
            //     return new ResponseEntity<>("Invalid Lien Lib format", HttpStatus.BAD_REQUEST);
            // }

            lienService.addNewLien(lienModel);

            return ResponseEntity.ok("Lien succesfully created");
            
        } catch (Exception e) {
            logger.error("Error while creating lien: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating lien.");
        }
        
    }

    @PutMapping("/api/lien/{code}")
    public ResponseEntity<String> updateLienData(@PathVariable Integer code, @RequestBody LienModel lienModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            
            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Lien Code format", HttpStatus.BAD_REQUEST);
            }
         
            // if(!UtilityMethods.validateInputString(lienModel.getLieLib(), 2, 255)) {
            //     return new ResponseEntity<>("Invalid Lien Lib format", HttpStatus.BAD_REQUEST);
            // }

            lienService.updateLienData(code, lienModel);

            return ResponseEntity.ok("Lien succesfully updated");
            
        } catch (Exception e) {
            logger.error("Error while updating lien: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating lien.");
        }
        
    }

    @DeleteMapping("/api/lien/{code}")
    public ResponseEntity<String> deleteLienData(@PathVariable Integer code) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

         
            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Lien Code format", HttpStatus.BAD_REQUEST);
            }

            lienService.deleteLienData(code);

            return ResponseEntity.ok("Lien succesfully deleted");
            
        } catch (Exception e) {
            logger.error("Error while deleting lien: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting lien.");
        }

    }


}
