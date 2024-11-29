package GHEBACKEND.GHEBACKEND;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonnelModel;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequest;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequestService;
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
	CommandLineRunner runner(InscriptionRequestService service){
		return args -> {
			InscriptionRequest request = new InscriptionRequest();
			service.inscrire(request);
		};
	}
}
