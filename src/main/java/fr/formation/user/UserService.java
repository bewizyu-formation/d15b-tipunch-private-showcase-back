package fr.formation.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Add a new user with the user repository
	 * 
	 * @param username
	 * @param password
	 * @param roles
	 */
	public void addNewUser(String username, String password, String... roles) {

		Collection<GrantedAuthority> authorities = null;

		userRepository.save(username, password, roles);

	}
}
