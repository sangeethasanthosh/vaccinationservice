package com.microservice.patters.vaccinationcentre.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateResponse {


    private VaccinationCentre vaccinationCentre;
    private List<Citizen> citizens;

}
