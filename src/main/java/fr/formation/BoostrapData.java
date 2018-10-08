package fr.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.formation.user.UserService;

/**
 * This class configure the dataset at application start
 *
 */
@Component
public class BoostrapData {

	private UserService userService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public BoostrapData(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {

		userService.addNewUser("admin", passwordEncoder.encode("admin"), "ADMIN");
		userService.addNewUser("user", passwordEncoder.encode("user"), "USER");

	}

}
