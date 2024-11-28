package GHEBACKEND.GHEBACKEND;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.service.PriseEnCharge.PersonnelService;


@SpringBootApplication
public class GhebackendApplication {
	
		public static void main(String[] args) {
		SpringApplication.run(GhebackendApplication.class, args); 
	}

	/* 
	 * 
	 * Test de service ajout personnel
	 */

	@Bean
	CommandLineRunner runner(PersonnelService service){
		return args -> {
			PersonnelModel personnel = new PersonnelModel();
			personnel.setPerPrenom("Wenz");
			personnel.setPerNom("Yannick");
			personnel.setPerEmail("yannick@gmail.com");
			personnel.setPerAdresse("3ème arrondissement, République centrafricaine-Bangui");
			personnel.setPerDateNais(LocalDate.of(1999,06,12));
			personnel.setPerCreerPar(null);
			personnel.setPerDateCreation(LocalDateTime.now());
			personnel.setPerSexe("Masculin");
			service.createPersonnel(personnel);
		};
	}
}
