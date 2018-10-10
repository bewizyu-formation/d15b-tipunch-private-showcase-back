package fr.formation.hello;

import java.io.IOException;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/admin")
	@Secured("ROLE_ADMIN")
	String sayHelloAdmin() {
		return "Hello Admin!";
	}

	@GetMapping("/user")
	@Secured("ROLE_USER")
	String sayHelloUser() {
		return "Hello User!";
	}

	@PostMapping("/upload")
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
			throws IOException {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();

			// For the test, we gave txt files that we can display
			// Remove all the code below for production !

			return new String(bytes, "UTF-8");
		}

		return "redirect:uploadFailure";
	}

	@GetMapping("/uploadFailure")
	public String uploadFailure() {
		return "Upload Failure !";

	}

}
