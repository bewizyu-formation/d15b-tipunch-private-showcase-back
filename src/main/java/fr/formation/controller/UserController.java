package fr.formation.controller;

import fr.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/users/{userId}")
public class UserController {


	private UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password,
			@RequestParam String... roles) {

		userService.addNewUser(username, password, roles);

	}

}
