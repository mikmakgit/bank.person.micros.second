package bank.person.micros.controllers;

import bank.person.micros.constants.Constants;
import bank.person.micros.entity.Person;
import bank.person.micros.services.*;
import bank.person.micros.types.Address;
import bank.person.micros.types.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    Address address;
    Gender gender;
    Person person;
    String validation;
    String errorMessage;

    @RequestMapping("/createperson")
    public String createperson (@RequestParam String name, @RequestParam int age, @RequestParam int genderId, @RequestParam double height,
                          @RequestParam double weight) {

        validation = validationCreateFields(name, age , genderId, height);
        if (!validation.equals(Constants.correct))
            return validation;

        address = new Address();
        gender = getGender(genderId);

        person = personService.createPerson(new Person(name, age, gender, height, weight, address));
        return person.toString();
    }

    @RequestMapping("/getperson")
    public String getPerson (@RequestParam String name) {

        personService.getPerson(name);

        return personService.getPerson(name).toString();
    }

    @RequestMapping("/getallpersons")
    public String getAllPersons () {

        personService.getAllPersons();

        return personService.getAllPersons().toString();
    }

    @RequestMapping("/updateperson")
    public String updateperson (@RequestParam String name, @RequestParam int age, @RequestParam int genderId, @RequestParam double height,
                          @RequestParam double weight) {
        validation = validationFields(age , genderId, height);
        if (!validation.equals("correct"))
            return validation;


        if (genderId < 0 && genderId > 2){
            return Constants.unrecognizedGender;
        }

        address = new Address();
        gender = getGender(genderId);

        personService.updatePerson(name, age, gender, height,weight);

        return personService.updatePerson(name, age, gender, height,weight).toString();
    }

    @RequestMapping("/deleteperson")
    public String deleteperson (@RequestParam String name) {

        personService.deleteOnePerson(name);

        return name + " was deleted";
    }

    @RequestMapping("/deleteallperson")
    public String deleteAllperson () {

        personService.deleteAllPersons();

        return "All persons were deleted";
    }

    private Gender getGender (int genderId){
        switch(genderId){
            case 0:
                gender = Gender.Male;
                break;
            case 1:
                gender = Gender.Female;
                break;
            case 2:
                gender = Gender.Other;
                break;
        }

        return gender;
    }

    private String validationFields(int age, int genderId, double height){

        // age validation
        if (age < 0 || age > 200){
            return Constants.incorrectAge;
        }

        // gender validation
        if (genderId < 0 || genderId > 2){
            return Constants.unrecognizedGender;
        }

        // height validation
        if (height < 0){
            return Constants.incorrectHeight;
        }

        return Constants.correct;
    }

    private String validationCreateFields(String name, int age, int genderId, double height){
        if(name.length() < 3 || name.length() > 20){
            return Constants.incorrectName;
        }

        errorMessage = validationFields (age, genderId, height);

        if (errorMessage.equals(Constants.correct)){
            return Constants.correct;
        } else
            return errorMessage;

    }

}
