package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class
 *
 * Spring and Spring Boot configures application automatically
 * Some aspects cane be configured with application.properties file
 * in classpath
 */
@SpringBootApplication
public class IotApplication {
	/** main function
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);
	}
}
