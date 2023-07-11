package com.Koupag;

import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Mappers.UserMapper;
import com.Koupag.Model.Roles;
import com.Koupag.Model.UserModel;
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

			Set<Roles> roles = new HashSet<>();
			roles.add(adminRole);

		};
	}

}
