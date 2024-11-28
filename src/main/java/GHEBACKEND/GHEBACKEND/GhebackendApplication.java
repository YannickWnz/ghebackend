package GHEBACKEND.GHEBACKEND;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.repository.PriseEnCharge.PersonnelRepository;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.PersonnelService;


@SpringBootApplication
public class GhebackendApplication {
	
		public static void main(String[] args) {
		SpringApplication.run(GhebackendApplication.class, args); 
	}

	@Bean
	CommandLineRunner runner(PersonnelService service){
		return args -> {
			PersonnelModel personnel = new PersonnelModel();
			personnel.setPerNom("Gaius Ocklefort");
			personnel.setPerEmail("gaiusyanbena11@gmail.com");
			personnel.setPerDateNais(LocalDate.of(2015,06,12));
			personnel.setPerCreerPar(null);
			personnel.setPerDateCreation(LocalDateTime.now());
			personnel.setPerSexe("Masculin");
			service.createPersonnel(personnel);
		};
	}
}
