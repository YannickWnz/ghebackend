package GHEBACKEND.GHEBACKEND;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequest;
import GHEBACKEND.GHEBACKEND.service.Inscription.InscriptionRequestService;
import GHEBACKEND.GHEBACKEND.utils.Utils;


@SpringBootApplication
public class GhebackendApplication {
	
		public static void main(String[] args) {
		SpringApplication.run(GhebackendApplication.class, args); 
		System.out.println(Utils.concatCurrentYearAndMonth());
	}

	/* 
	 * Test de service ajout personnel
	 * @GaiusYan
	 */

/* 	@Bean
	CommandLineRunner runner(InscriptionRequestService service){
		return args -> {
			InscriptionRequest request = new InscriptionRequest();
			service.inscrire(request);
		};
	} */
}
