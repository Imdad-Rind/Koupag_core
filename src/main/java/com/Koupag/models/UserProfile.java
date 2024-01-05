package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserProfileTable")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID ID;

	@OneToOne(targetEntity = User.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "User_Column")
	private User user;
}
