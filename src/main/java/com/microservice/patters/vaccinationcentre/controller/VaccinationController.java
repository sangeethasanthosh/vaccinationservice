package com.microservice.patters.vaccinationcentre.controller;


import com.microservice.patters.vaccinationcentre.entity.AggregateResponse;
import com.microservice.patters.vaccinationcentre.entity.VaccinationCentre;
import com.microservice.patters.vaccinationcentre.service.VaccionationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccinationcentre")
public class VaccinationController {

    @Autowired
    VaccionationService vaccionationService;


    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/save")
    public ResponseEntity<String> saveCitizen(@RequestBody VaccinationCentre vaccinationCentre){
        vaccionationService.save(vaccinationCentre);
        return new ResponseEntity<>("Saved vaccinationCentre to Repo", HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @CircuitBreaker(name="CITIZEN_SERVICE",fallbackMethod="fetchDefault")
    public ResponseEntity<AggregateResponse> getCompleteDetails(@PathVariable Integer id){
        AggregateResponse aggregateResponse = new AggregateResponse();

        aggregateResponse.setVaccinationCentre(vaccionationService.getVaccinationCentreDetails(id));
       if(aggregateResponse.getVaccinationCentre()!=null) {
           System.out.println("Calling Citizen Service");
           //List citizenList = restTemplate.getForObject("http://CITIZEN_SERVICE/citizen/id/" + id, List.class);
           List citizenList = restTemplate.getForObject("http://localhost:8081/citizen/id/" + id, List.class);
           aggregateResponse.setCitizens(citizenList);
       }

            return new ResponseEntity<>(aggregateResponse, HttpStatus.OK);
    }

    public ResponseEntity<AggregateResponse> fetchDefault(@PathVariable Integer id,Exception exception){
       System.out.println("CitizenServiceDown");
        AggregateResponse aggregateResponse = new AggregateResponse();

        aggregateResponse.setVaccinationCentre(vaccionationService.getVaccinationCentreDetails(id));
        return new ResponseEntity<>(aggregateResponse, HttpStatus.OK);
    }
}
