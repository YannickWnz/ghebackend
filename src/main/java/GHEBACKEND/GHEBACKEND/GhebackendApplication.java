package GHEBACKEND.GHEBACKEND;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"GHEBACKEND.GHEBACKEND.model"})
public class GhebackendApplication {
	
		public static void main(String[] args) {
		SpringApplication.run(GhebackendApplication.class, args); 
	}

	/* 
	 * 
	 * Test de service ajout personnel
	 */

	/* @Bean
	CommandLineRunner runner(PersonnelService service){
		return args -> {
			PersonnelModel personnel = new PersonnelModel();
			personnel.setPerPrenom("User");
			personnel.setPerNom("oni");
			personnel.setPerEmail("devoni@gmail.com");
			personnel.setPerAdresse("1er arrondissement, République centrafricaine-Bangui");
			personnel.setPerDateNais(LocalDate.of(2003,06,12));
			personnel.setPerCreerPar(null);
			personnel.setPerDateCreation(LocalDateTime.now());
			personnel.setPerSexe("Masculin");
			service.createPersonnel(personnel);
		};
	} */
}
