package fr.formation.security;

import static java.util.Collections.emptyList;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This service manages the JWT token authentication and storage.
 */
public class AuthenticationService {

	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret"; // todo : replace with env token
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static final String CLAIM_ROLES = "roles";

	/**
	 * Add a JWT to the current response as a header with name {@link HEADER_STRING}
	 * and value {@link TOKEN_PREFIX}+ the token hashed with args :
	 * {@link SignatureAlgorithm.HS512}, {@link SECRET} which value must be given
	 * through an env var at application building
	 * 
	 * @param res
	 * @param username
	 */
	static void addAuthentication(HttpServletResponse res, String username,
			Collection<? extends GrantedAuthority> roles) {
		List<String> rolesString = roles.parallelStream().map(roleValue -> roleValue.getAuthority())
				.collect(Collectors.toList());

		String JWT = Jwts.builder().setSubject(username).claim("roles", rolesString)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	/**
	 * Extract the current {@link Authentication} from the incoming request
	 * 
	 * @param request
	 * @return
	 */
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			String user = claims.getSubject();
			Collection<String> rolesString = claims.get(CLAIM_ROLES, Collection.class);

			Collection<GrantedAuthority> roles = rolesString.parallelStream()
					.map(roleValue -> new SimpleGrantedAuthority("ROLE_"+roleValue)).collect(Collectors.toList());

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, roles) : null;
		}
		return null;
	}
}
