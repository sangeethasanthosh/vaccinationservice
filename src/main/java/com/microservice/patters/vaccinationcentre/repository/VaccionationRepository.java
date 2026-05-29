package com.microservice.patters.vaccinationcentre.repository;

import com.microservice.patters.vaccinationcentre.entity.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccionationRepository  extends JpaRepository<VaccinationCentre,Integer> {
}
