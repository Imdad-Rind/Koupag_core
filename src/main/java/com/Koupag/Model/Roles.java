package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles  implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String authority;

    public Roles(String authority) {
        this.authority = authority;
    }
}
