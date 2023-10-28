package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserProfileTable")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long ID;
	private String name;
	@JsonBackReference
	@OneToOne(targetEntity = Address.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_address")
	private Address address;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "User_Column")
	private User user;
}
