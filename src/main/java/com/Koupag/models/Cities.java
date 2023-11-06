package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long Id;
	String name;
	
	@JsonManagedReference
	@OneToMany(targetEntity = Address.class,fetch = FetchType.EAGER,mappedBy = "city")
	List<Address> address;
	
	public Cities(String name, List<Address> address) {
		this.name = name;
		this.address = address;
	}
	
	public Cities(String name) {
		this.name = name;
	}
}
