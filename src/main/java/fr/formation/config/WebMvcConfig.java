package fr.formation.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web MVC Configuration. This method replaces the traditional web.xml
 * deployment descriptor.
 * 
 *
 */
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS")
				.allowCredentials(true);
	}

}
