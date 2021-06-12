package bank.person.micros;

import bank.person.micros.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		// get data from the first microservice
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Person[]> response = restTemplate.getForEntity(
				"http://localhost:8081/getallpersons", Person[].class);
		Person[] persons = response.getBody();

	}

}
