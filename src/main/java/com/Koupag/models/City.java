package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long Id;
	String name;

	
	public City(String name, List<Address> address) {
		this.name = name;
	}
	
	public City(String name) {
		this.name = name;
	}
}
