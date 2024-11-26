package GHEBACKEND.GHEBACKEND.service.PriseEnCharge;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonnelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository personnelRepository;
}
