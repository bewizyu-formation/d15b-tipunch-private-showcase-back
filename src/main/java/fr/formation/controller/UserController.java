package fr.formation.controller;

import fr.formation.model.User;
import fr.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/{userId}")
	public User findOne(@PathVariable String userId){
		return userService.findOne(Long.parseLong(userId));
	}

	@GetMapping()
	public List<User> findAll(){
		return userService.findAll();
	}

	@PostMapping()
	public void signup(@RequestBody User user, @RequestParam String... roles) {

		userService.save(user, roles);
	}

	@PutMapping("/{userId}")
	public void update(@PathVariable String userId, @RequestBody User user){
		userService.udpate(Long.parseLong(userId), user);
	}

	@DeleteMapping("/{userId}")
	public void delete(@PathVariable String userId){
		userService.deleteById(Long.parseLong(userId));
	}
}
