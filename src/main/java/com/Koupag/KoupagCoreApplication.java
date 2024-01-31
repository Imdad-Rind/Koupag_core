package com.Koupag;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.models.*;
import com.Koupag.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class KoupagCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(KoupagCoreApplication.class, args);
	}

	@Bean
	CommandLineRunner run(SurplusMaterialServices service,RolesService rolesService, CitiesServices citiesServices,AuthenticationService auth){
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
			citiesServices.addNewCity(new City("Turbat"));
			citiesServices.addNewCity(new City("Quetta"));
			citiesServices.addNewCity(new City("Khuzdar"));
			citiesServices.addNewCity(new City("Sibi"));
			citiesServices.addNewCity(new City("Gwadar"));
			citiesServices.addNewCity(new City("Panjgur"));
			citiesServices.addNewCity(new City("Hub"));
			dummySurplusMaterials(service);
			addDummyUser(auth);
		};
	}

	// For debugging purposes

	void addDummyUser(AuthenticationService auth){
		try{
			User user1 = new User();
			Address user1address = new Address(
					"Gebon",
					"Turbat",
					null,
					user1
			);
			Location user1addressLocation = new Location(
					"23.3234",
					"12.2343",
					user1address
			);
			// dummy donor
			user1address.setLocation(user1addressLocation);
			user1.setName("Baloch");
			user1.setCNIC("5220358009153");
			user1.setPhoneNumber("03232579202");
			user1.setEmail("balochrasheed@gmail.com");
			user1.setPassword("donor");
			user1.setUserType("DONOR");
			user1.setAddress(user1address);
			auth.registerUser(user1);
			Optional<LoginResponseDTO> loginUser = auth.loginUser(new LoginDTO("5220358009153","donor"));
			System.out.println("The Donor JWT Token ["+loginUser.get().getJwt()+"]");
			// dummy volunteer
			user1address.setAreaName("Dabbok");
			user1address.setLocation(user1addressLocation);
			user1.setName("Komar");
			user1.setCNIC("5220358009154");
			user1.setPhoneNumber("03232579204");
			user1.setEmail("balochrasheed4@gmail.com");
			user1.setPassword("volunteer");
			user1.setUserType("VOLUNTEER");
			user1.setAddress(user1address);
			auth.registerUser(user1);
			loginUser = auth.loginUser(new LoginDTO("5220358009154","volunteer"));
			System.out.println("The Volunteer JWT Token ["+loginUser.get().getJwt()+"]");
			// dummy recipient
			user1address.setAreaName("Gebon");
			user1address.setLocation(user1addressLocation);
			user1.setName("Javid");
			user1.setCNIC("5220358009155");
			user1.setPhoneNumber("03232579205");
			user1.setEmail("balochrasheed5@gmail.com");
			user1.setPassword("recipient");
			user1.setUserType("RECIPIENT");
			user1.setAddress(user1address);
			auth.registerUser(user1);
			loginUser = auth.loginUser(new LoginDTO("5220358009155","recipient"));
			System.out.println("The Recipient JWT Token ["+loginUser.get().getJwt()+"]");

		} catch (Exception e){

		}
	}

	void dummySurplusMaterials(SurplusMaterialServices service){
		service.createNewSurplusMaterial(new SurplusMaterial(
				"Food",
				"Eatable meals"
		));
		service.createNewSurplusMaterial(new SurplusMaterial(
				"Clothe",
				"Wearables Material"
		));
		service.createNewSurplusMaterial(new SurplusMaterial(
				"Money",
				"Cash Resource"
		));
		service.createNewSurplusMaterial(new SurplusMaterial(
				"Gift",
				"Unknown Present"
		));
		service.createNewSurplusMaterial(new SurplusMaterial(
				"Other",
				"User Defined"
		));
	}
}
