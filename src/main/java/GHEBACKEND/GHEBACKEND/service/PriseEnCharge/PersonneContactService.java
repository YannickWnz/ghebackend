package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentContactDataProjection;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonneContactRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class PersonneContactService {

    @Autowired
    private PersonneContactRepo personneContactRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    // public void addNewContact()
    public List<StudentContactDataProjection> getStudentContacts(Integer code) {

        if(code == null) {
            throw new IllegalArgumentException("Invalid contact code");
        }

        return personneContactRepo.getContactsByStudentCode(code);

    }


}
