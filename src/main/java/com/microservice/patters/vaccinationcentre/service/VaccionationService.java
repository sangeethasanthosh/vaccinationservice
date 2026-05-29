package com.microservice.patters.vaccinationcentre.service;


import com.microservice.patters.vaccinationcentre.entity.VaccinationCentre;
import com.microservice.patters.vaccinationcentre.repository.VaccionationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccionationService {

    @Autowired
    VaccionationRepository vaccionationRepository;

    public void save(VaccinationCentre vaccinationCentre) {
        vaccionationRepository.save(vaccinationCentre);
    }

    public VaccinationCentre getVaccinationCentreDetails(int id){
        Optional<VaccinationCentre> vaccinationCentreOptional=vaccionationRepository.findById(id);
        return vaccinationCentreOptional.orElse(null);
    }
}
