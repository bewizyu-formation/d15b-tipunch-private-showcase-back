package fr.formation.hello;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/admin")
	@Secured("ROLE_ADMIN")
	String sayHelloAdmin() {
		return "Hello Admin!";
	}
	
	@GetMapping("/user")
	@Secured("ROLE_USER")
	String sayHelloUser() {
		return "Hello User!";
	}
	
}
