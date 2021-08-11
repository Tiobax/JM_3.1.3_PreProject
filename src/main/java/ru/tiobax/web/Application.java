package ru.tiobax.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tiobax.web.role.Role;
import ru.tiobax.web.role.RoleService;
import ru.tiobax.web.role.RoleServiceImpl;
import ru.tiobax.web.user.User;
import ru.tiobax.web.user.UserServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserServiceImpl userService, RoleServiceImpl roleService) {
		return args -> {
			roleService.addNewRole(new Role(null, "ROLE_USER"));
			roleService.addNewRole(new Role(null, "ROLE_ADMIN"));

			userService.addNewUser(new User(null, "zederon@mail.ru", "Evgeny", "Buko", LocalDate.of(1987, Month.FEBRUARY, 1), "123321", true, new HashSet<>()));
			userService.addNewUser(new User(null, "pupkin@mail.ru", "Vasia", "Pupkin", LocalDate.of(1911, Month.APRIL, 1), "123321", true, new HashSet<>()));
			userService.addNewUser(new User(null, "gadia@mail.ru", "Gadia", "Hrenova", LocalDate.of(1999, Month.AUGUST, 23), "123321", false, new HashSet<>()));

			userService.addRoleToUser("zederon@mail.ru", "ROLE_USER");
			userService.addRoleToUser("zederon@mail.ru", "ROLE_ADMIN");
			userService.addRoleToUser("pupkin@mail.ru", "ROLE_USER");
			userService.addRoleToUser("gadia@mail.ru", "ROLE_USER");
		};
	}
}
