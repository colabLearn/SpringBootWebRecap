package com.tutorial.tunjiTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class TunjiTechApplication {

	public static void main(String[] args) {

		SpringApplication.run(TunjiTechApplication.class, args);
	}

	@GetMapping("/greet")
	public GreetResponse greet()
	{
		GreetResponse response = new GreetResponse("Hello",
				List.of("Java", "Golan", "JavaScript"),
				new Person("Alex")
		);
		return response;
	}

	record Person(String name){}
	record GreetResponse(
			String greet,
			List<String> favProgLang,
			Person person){

	}
}
