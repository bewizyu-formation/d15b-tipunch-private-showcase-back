package fr.formation.user;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


@Repository
public class UserRepository {

	private UserDetailsManager userDetailManager;

	public UserRepository() {
		userDetailManager = new InMemoryUserDetailsManager();

		// TODO : only for starter and tests
		save("admin", "{noop}admin", "ADMIN");
		save("user", "{noop}user", "USER");
	}

	public UserDetails findByUsername(String username) {
		return userDetailManager.loadUserByUsername(username);
	}

	/**
	 * Save a new user built with the args. {@link ApplicationRoles#USER} is the
	 * default role if no authorities are defined.
	 * 
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public void save(String username, String password, String... authorities) {
		UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username)//
				.password(password)//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false);

		if (authorities != null) {
			Collection<String> authoritiesCollection = CollectionUtils.arrayToList(authorities);

			if (!CollectionUtils.isEmpty(authoritiesCollection)) {
				userBuilder.authorities(authorities);
			} else {
				userBuilder.authorities(new SimpleGrantedAuthority("USER"));
			}
		}

		userDetailManager.createUser(userBuilder.build());
	}
}
