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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeDocumentModel;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.TypeDocumentService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TypeDocumentController {

    @Autowired
    private TypeDocumentService typeDocumentService;

    @Autowired
    private UtilityMethods utilityMethods;
    
    @GetMapping("/api/typeDocument/totalCount") 
    public Integer getTypeDocTotalCount() {
        return typeDocumentService.getTotalDataNumber("T_TYPE_DOCUMENT");
    }

    @GetMapping("/api/typeDocument")
    public List<TypeDocumentModel> getAllTypeDocData() {
        return typeDocumentService.getAllTypeDocsData();
    }

    @PostMapping("/api/typeDocument")
    public ResponseEntity<String> addNewEmploi(@RequestBody TypeDocumentModel typeDocumentModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

         
            if(!UtilityMethods.validateInputString(typeDocumentModel.getTdcLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            typeDocumentService.addNewTypeDocs(typeDocumentModel);

            return ResponseEntity.ok("Type Document succesfully created");
            
        } catch (Exception e) {
            logger.error("Error while creating type doc: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating type doc.");
        }
    }

    @PutMapping("/api/typeDocument/{code}")
    public ResponseEntity<String> updateTypeDocumentData(@PathVariable Integer code, @RequestBody TypeDocumentModel typeDocumentModel) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
         
            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Code format", HttpStatus.BAD_REQUEST);
            }

            if(!UtilityMethods.validateInputString(typeDocumentModel.getTdcLib(), 2, 100)) {
                return new ResponseEntity<>("Invalid Lib format", HttpStatus.BAD_REQUEST);
            }

            typeDocumentService.updateTypeDocsData(code, typeDocumentModel);

            return ResponseEntity.ok("Type Doc succesfully updated");
            
        } catch (Exception e) {
            logger.error("Error while updated type doc: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updated type doc.");
        }

    }

    @DeleteMapping("/api/typeDocument/{code}")
    public ResponseEntity<String> deleteTypeDocumentData(@PathVariable Integer code) {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {

            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Code format", HttpStatus.BAD_REQUEST);
            }
            
            typeDocumentService.deleteTypeDocData(code);

            return ResponseEntity.ok("Type doc succesfully deleted");
            
        } catch (Exception e) {
            logger.error("Error while deleting data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleted data.");
        }

    }


}
