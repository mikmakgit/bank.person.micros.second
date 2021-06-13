package bank.person.micros.controllers;

import bank.person.micros.constants.Constants;
import bank.person.micros.entity.Person;
import bank.person.micros.services.*;
import bank.person.micros.types.Address;
import bank.person.micros.types.enums.Gender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PersonController {

    RestTemplate restTemplate;
    ResponseEntity<List> response;

    @Autowired
    private PersonService personService;

    @RequestMapping("/getallpersons")
    public String getAllPersons () throws JsonProcessingException {
        restTemplate = new RestTemplate();
        response = (ResponseEntity<List>) restTemplate.getForEntity("http://localhost:8081/getallpersons", List.class);
        ObjectMapper om = new ObjectMapper();

        return om.writeValueAsString(response.getBody());
    }

    @RequestMapping("/getallpersonbychar")
    public String getAllPersonByChar () throws JsonProcessingException {
        restTemplate = new RestTemplate();
        response = (ResponseEntity<List>) restTemplate.getForEntity("http://localhost:8081/getallpersonbychar", List.class);
        ObjectMapper om = new ObjectMapper();

        return om.writeValueAsString(response.getBody());
    }

    @RequestMapping("/allavgcit")
    public String getAllIsraelCitizensAboveAverageWeight () throws JsonProcessingException {
        restTemplate = new RestTemplate();
        response = (ResponseEntity<List>) restTemplate.getForEntity("http://localhost:8081/allavgcit", List.class);
        ObjectMapper om = new ObjectMapper();

        return om.writeValueAsString(response.getBody());
    }

}
