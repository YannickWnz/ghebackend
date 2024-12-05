package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonneContactRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class PersonneContactService {

    @Autowired
    private PersonneContactRepo personneContactRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    // public void addNewContact()


}
