package fr.formation.controller;

import fr.formation.model.User;
import fr.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


	private UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@Secured("ROLE_USER")
	@GetMapping("/{userId}")
	public User findOne(@PathVariable String userId){
		return userService.findOne(Long.parseLong(userId));
	}

	@Secured("ROLE_USER")
	@GetMapping()
	public List<User> findAll(){
		return userService.findAll();
	}

	@PostMapping()
	public void signup(@RequestBody User user, @RequestParam String... roles) {

		userService.save(user, roles);
	}

	@Secured("ROLE_USER")
	@PutMapping("/{userId}")
	public void update(@PathVariable Long userId, @RequestBody User user){
		userService.udpate(userId, user);
	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{userId}")
	public void delete(@PathVariable String userId){
		userService.deleteById(Long.parseLong(userId));
	}
}
