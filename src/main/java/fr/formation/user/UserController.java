package fr.formation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password,
			@RequestParam String... roles) {

		userService.addNewUser(username, password, roles);

	}

}
