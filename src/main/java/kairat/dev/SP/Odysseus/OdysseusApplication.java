package kairat.dev.SP.Odysseus;

import kairat.dev.SP.Odysseus.Models.Role;
import kairat.dev.SP.Odysseus.Models.User;
import kairat.dev.SP.Odysseus.Repositories.UserRepository;
import kairat.dev.SP.Odysseus.Services.UserService;
import kairat.dev.SP.Odysseus.configs.MySSUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class OdysseusApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(OdysseusApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner setupDefaultUser(UserService service) {
//		return args -> {
//			service.save(new User(
//					"admin", //username
//					"admin", //password
//					Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles
//					true//Active
//			));
//		};
//	}

	/**
	 * Password grants are switched on by injecting an AuthenticationManager.
	 * Here, we setup the builder so that the userDetailsService is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
	 */
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
		if (repository.count()==0)
			userService.save(new User("admin", "adminPassword", Arrays.asList(new Role("USER"),
					new Role("ACTUATOR") , new Role("ADMIN")), true));
		userService.save(new User("userOne", "userOnePassword", Arrays.asList(new Role("USER"),
				new Role("ACTUATOR")), true));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}


	/**
	 * We return an istance of our CustomUserDetails.
	 * @param repository
	 * @return
	 */
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new MySSUserDetails(repository.findByUsername(username));
	}
}
