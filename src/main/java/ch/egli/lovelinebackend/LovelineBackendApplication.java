package ch.egli.lovelinebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "ch.egli.lovelinebackend.*" })
public class LovelineBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovelineBackendApplication.class, args);
	}

}
