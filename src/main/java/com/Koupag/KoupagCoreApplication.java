package com.Koupag;

import com.Koupag.Model.Roles;
import com.Koupag.Services.RolesService;
import com.Koupag.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class KoupagCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoupagCoreApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RolesService rolesService, UserService userService){
		return args ->{
			if(rolesService.getByName("ADMIN").isPresent()) return;
			Roles adminRole = rolesService.CreateNewRole(new Roles("ADMIN"));
			rolesService.CreateNewRole(new Roles("USER"));
			rolesService.CreateNewRole(new Roles("DONOR"));
			rolesService.CreateNewRole(new Roles("VOLUNTEER"));
			rolesService.CreateNewRole(new Roles("RECIPIENT"));
			rolesService.CreateNewRole(new Roles("ORGANIZATION"));

			Set<Roles> roles = new HashSet<>();
			roles.add(adminRole);

//			userService.creteNewUser(new UserModel(
//					"Admin",
//					"03232579202",
//					"balochrasheed888@gmail.com",
//					"admin",
//					"password",
//					roles
//			));
		};
	}

}
