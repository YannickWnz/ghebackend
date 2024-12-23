package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueDataProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.RubriqueRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class RubriqueService {

    @Autowired
    private RubriqueRepo rubriqueRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RubriqueDataProjection> getRubriqueData() {
        return rubriqueRepo.getRubriqueAndClasseData();
    }

    public List<RubriqueModel> getAllRubrique() {
        return rubriqueRepo.findAll();
    }

    @SuppressWarnings("deprecation")
    public Integer existingPaymentOrder(Integer paymentOrder) {
        
        try {

            String getExistingPaymentOrder = "SELECT RUB_CODE FROM T_RUBRIQUE WHERE RUB_ORDRE_PAYMENT = ?";

            
            if(getExistingPaymentOrder != null) {

                String getMaxPaymentOrder = "SELECT MAX(RUB_ORDRE_PAYMENT) FROM T_RUBRIQUE";

                Integer maxPaymentOrder = jdbcTemplate.queryForObject(getMaxPaymentOrder, new Object[]{}, Integer.class);

                Integer newPaymentOrder = maxPaymentOrder + 1;

                String updateOrderPaymentQuery = "UPDATE T_RUBRIQUE SET RUB_ORDRE_PAYMENT = ? WHERE RUB_CODE = ?";

                jdbcTemplate.update(updateOrderPaymentQuery, newPaymentOrder, getExistingPaymentOrder);

            } 

            return jdbcTemplate.queryForObject(getExistingPaymentOrder, new Object[]{paymentOrder}, Integer.class);
        
        } 
        catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @SuppressWarnings("deprecation")
    public void addNewRubrique(RubriqueModel rubriqueModel) {

        Integer code = utilityMethods.codeGenerator("RUB_CODE", "T_RUBRIQUE");

        // version definie sur 1 par default a la creation
        Integer version = 1;
    
        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();

        // HANDLING RUB ORDER PAYMENT STARTS
        if(!rubriqueModel.getRubFraisUnique()) {
   
            int rub_order_payment = rubriqueModel.getRubOrdrePaiement();

            String getExistingPaymentOrder = "SELECT RUB_CODE FROM T_RUBRIQUE WHERE RUB_ORDRE_PAYMENT = ?";

            Integer getExistingOrderCodeResult = jdbcTemplate.queryForObject(getExistingPaymentOrder, new Object[]{rub_order_payment}, Integer.class);
                
            if(getExistingOrderCodeResult != null) {

                String getMaxPaymentOrder = "SELECT MAX(RUB_ORDRE_PAYMENT) FROM T_RUBRIQUE";

                Integer maxPaymentOrder = jdbcTemplate.queryForObject(getMaxPaymentOrder, new Object[]{}, Integer.class);

                Integer newPaymentOrder = maxPaymentOrder + 1;

                String updateOrderPaymentQuery = "UPDATE T_RUBRIQUE SET RUB_ORDRE_PAYMENT = ? WHERE RUB_CODE = ?";

                jdbcTemplate.update(updateOrderPaymentQuery, newPaymentOrder, getExistingOrderCodeResult);

            }
        } 
        else {
            rubriqueModel.setRubOrdrePaiement(null);
        } 
        // HANDLING RUB ORDER PAYMENT ENDS


        rubriqueModel.setRubCode(code);
        rubriqueModel.setRubVersion(version);
        rubriqueModel.setRubDateCreation(currentDateTime);

        rubriqueRepo.save(rubriqueModel);

    }

    public void updateRubriqueData(Integer code, RubriqueModel rubriqueModel) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "RUB_CODE", "T_RUBRIQUE", "RUB_VERSION") + 1;

        // find data before update else throw error
        RubriqueModel existingRubriqueData = rubriqueRepo.findById(code)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        
        // set new data then save ...
            existingRubriqueData.setRubLib(rubriqueModel.getRubLib());
            existingRubriqueData.setRubMontant(rubriqueModel.getRubMontant());
            existingRubriqueData.setRubFraisUnique(rubriqueModel.getRubFraisUnique());
            existingRubriqueData.setRubModifierPar(rubriqueModel.getRubModifierPar());
            existingRubriqueData.setRubVersion(newVersion);

            rubriqueRepo.save(existingRubriqueData);

    }

    public void deleteRubriqueData(int code) {

        if(rubriqueRepo.existsById(code)) {
            rubriqueRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

    @Transactional
    public void updateRubFraisUnique(Integer code, RubriqueModel rubriqueModel) {

        rubriqueRepo.updateRubriqueFraisUnique(code, rubriqueModel.getRubFraisUnique(), rubriqueModel.getRubModifierPar());

    }
    
    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }


    public List<RubriqueModel> recupererRubriquePourUneClasse(Integer code) {

        return rubriqueRepo.recupererRubriquePourUneClasse(code);

    }

    public void addDefaultPaymentOrder() {}

    public void setOrderPayment() {

        List<RubriqueModel> rubriqueData = rubriqueRepo.findAll();

    }

    public List<RubriqueModel> getRubriqueModelByClasse(ClasseModel classeModel){
        return rubriqueRepo.findByClasse(classeModel);
    }

}
