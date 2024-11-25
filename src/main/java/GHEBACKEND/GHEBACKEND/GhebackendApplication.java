package GHEBACKEND.GHEBACKEND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.DirectionRepository;


@SpringBootApplication
@RestController
public class GhebackendApplication {
	@Autowired
	private DirectionRepository directionRepository;


	public GhebackendApplication(DirectionRepository directionRepository){
		this.directionRepository = directionRepository;
	}
	public static void main(String[] args) {
		
		SpringApplication.run(GhebackendApplication.class, args); 
		
	}

}
