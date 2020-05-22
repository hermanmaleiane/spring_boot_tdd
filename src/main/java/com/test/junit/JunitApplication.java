package com.test.junit;

import com.test.junit.model.User;
import com.test.junit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitApplication.class, args);
	}


	@Bean
	public CommandLineRunner setup(UserRepository repository) {
		return (args) -> {
			repository.save(new User("Herman", 27,"Matola"));
			repository.save(new User("Miton", 27,"Fomento"));
			repository.save(new User("Maleiane", 27,"Maputo"));

		};
	}

}
