package es.um.atica.umuexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"es.um.atica.umuexample","es.um.atica.umubus"})
public class UmuBusPruebasApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UmuBusPruebasApplication.class, args);
	}

}
