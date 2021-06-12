package bank.person.micros.services;

import bank.person.micros.entity.Person;
import bank.person.micros.repository.PersonRepository;
import bank.person.micros.types.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // create single person
    public Person createPerson (Person person){
        return personRepository.save(person);
    }

    // single
    public Person getPerson(String name){
        return personRepository.findByName(name);
    }

    // read multiple person
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

//    // read all people whose name start with A or K
//    public List<Person> getAllPersonsByFirstCharName(char firsCharName){
//        return personRepository.findByFirstCharName('a', 'b');
//    }

    // update single person
    public Person updatePerson (String name, int age, Gender gender, double height, double weight){
        Person p = personRepository.findByName(name);
        p.setName(name);
        p.setAge(age);
        p.setGender(gender);
        p.setHeight(height);
        p.setWeight(weight);
        return personRepository.save(p);
    }

    // delete single person
    public void deleteOnePerson(String name){
        Person p = personRepository.findByName(name);
        personRepository.delete(p);
    }

    // delete all person
    public void deleteAllPersons(){
        personRepository.deleteAll();
    }
}
