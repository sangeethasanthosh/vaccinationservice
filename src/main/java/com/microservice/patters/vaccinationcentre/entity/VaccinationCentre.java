package com.microservice.patters.vaccinationcentre.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String  vaccinationCentreName;


    @Column
    private String  vaccinationAddress;


}
