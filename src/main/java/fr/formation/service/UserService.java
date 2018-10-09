package fr.formation.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import fr.formation.model.User;
import fr.formation.repository.UserRepository;
import fr.formation.model.UserRole;
import fr.formation.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(login);

		if (user != null) {
			List<String> roles = userRoleRepository.findRoleByUserLogin(login);
			return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
					transformToAuthorities(roles));
		} else {
			throw new UsernameNotFoundException("No user exists with username: " + login);
		}

	}

	/**
	 * Add a new user with the user repository
	 * 
	 * @param user
	 * @param roles
	 */
	public void save(User user, String... roles) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(user.getPassword());
	    user.setPassword(encryptedPassword);
		user = userRepository.save(user);

		for (String role : roles) {

			UserRole userRole = new UserRole();
			userRole.setRole(role.toUpperCase());
			userRole.setUserId(user.getId());

			userRoleRepository.save(userRole);
		}

	}
	public User findOne(Long userId){
		Optional<User> optionalUser = userRepository.findById(userId);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}
	public List<User> findAll(){
		return this.userRepository.findAll();
	}

	public void udpate(Long userId, User user){
		Optional<User> optionalUser = userRepository.findById(user.getId());
		optionalUser.ifPresent(u -> {
			u = user;
			this.save(u);
		} );
	}

	public void deleteById(Long userId){
		userRoleRepository.deleteByUserId(userId);
		userRepository.deleteById(userId);
	}
	/**
	 * transform a list of roles (as {@link String}) into a list of
	 * {@link GrantedAuthority}
	 * 
	 * @param userRoles
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> transformToAuthorities(List<String> userRoles) {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}
}