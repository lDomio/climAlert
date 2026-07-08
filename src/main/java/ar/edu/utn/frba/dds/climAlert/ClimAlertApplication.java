package ar.edu.utn.frba.dds.climAlert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClimAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimAlertApplication.class, args);
	}

}
