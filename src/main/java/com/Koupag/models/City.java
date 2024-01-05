package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID Id;
	String name;

	
	public City(String name, List<Address> address) {
		this.name = name;
	}
	
	public City(String name) {
		this.name = name;
	}
}
