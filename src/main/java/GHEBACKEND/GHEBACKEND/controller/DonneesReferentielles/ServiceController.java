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

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ServiceDataProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ServiceModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.ServiceRepo;
import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.ServiceService;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private ServiceRepo serviceRepo;

    @GetMapping("/api/service/totalCount")
    public Integer getServiceTotalCount() {
        return serviceService.getTotalDataNumber("T_SERVICE");
    }

    @GetMapping("/api/service/data")
    public List<ServiceDataProjection> getServiceData() {

        return serviceService.getServiceData();

    }

    @GetMapping("/api/service")
    public List<ServiceModel> getAllServiceData() {

        return serviceRepo.findAll();

    }

    @GetMapping("/api/service/{dirCode}")
    public List<ServiceModel> addNewServiceData(@PathVariable Integer dirCode) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        if(!UtilityMethods.validateInputCode(Integer.toString(dirCode))) {
            logger.error("Invalid code format");
        }

        return serviceService.getAllServiceInSelectedDirection(dirCode);

    }

    @PostMapping("/api/service")
    public ResponseEntity<String> addNewServiceData(@RequestBody ServiceModel serviceModel) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            if(!UtilityMethods.validateInputCode(Integer.toString(serviceModel.getDirCode()))) {
                return new ResponseEntity<>("Invalid Service Code format", HttpStatus.BAD_REQUEST);
            }
            
            // if(!UtilityMethods.validateInputString(serviceModel.getSceLib(), 2, 255)) {
            //     return new ResponseEntity<>("Invalid Service Lib format", HttpStatus.BAD_REQUEST);
            // }

            serviceService.addNewService(serviceModel.getDirCode(), serviceModel);

            return ResponseEntity.ok("Service succesfully created");


        } catch (Exception e) {
        
            logger.error("Error while creating service: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating service.");
        
        }

    }

    @PutMapping("/api/service/{code}")
    public ResponseEntity<String> updateServiceData(@PathVariable Integer code, @RequestBody ServiceModel serviceModel) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Code format", HttpStatus.BAD_REQUEST);
            }
            
            // if(!UtilityMethods.validateInputString(serviceModel.getSceLib(), 2, 255)) {
            //     return new ResponseEntity<>("Invalid Service Lib format", HttpStatus.BAD_REQUEST);
            // }

            serviceService.updateServiceData(code, serviceModel);

            return ResponseEntity.ok("Service succesfully updated");


        } catch (Exception e) {
        
            logger.error("Error while updating service: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating service.");
        
        }

    }

    @DeleteMapping("/api/service/{code}")
    public ResponseEntity<String> deleteServiceData(@PathVariable Integer code) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            
            if(!UtilityMethods.validateInputCode(Integer.toString(code))) {
                return new ResponseEntity<>("Invalid Code format", HttpStatus.BAD_REQUEST);
            }

            serviceService.deleteServiceData(code);

            return ResponseEntity.ok("Service succesfully deleted");


        } catch (Exception e) {
        
            logger.error("Error while deleting service: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting service.");
        
        }

    }




}
