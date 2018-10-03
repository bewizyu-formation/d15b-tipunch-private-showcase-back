package fr.formation.hello;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;


	@Test
	public void shouldReturnHelloAdmin() throws Exception {

		MvcResult mvcResult = mvc.perform(formLogin("/login").user("admin").password("admin")).andReturn();

		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

		mvc.perform(get("/hello/admin").header("Authorization", authorizationHeader)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello Admin!")))
				.andExpect(authenticated().withUsername("admin"));
		
		mvc.perform(get("/hello/user").header("Authorization", authorizationHeader)).andExpect(status().is(403));
	}
	
	@Test
	public void shouldForbidAdminToHelloUser() throws Exception {

		MvcResult mvcResult = mvc.perform(formLogin("/login").user("admin").password("admin")).andReturn();

		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");
		
		mvc.perform(get("/hello/user").header("Authorization", authorizationHeader)).andExpect(status().is(403));
	}

}
