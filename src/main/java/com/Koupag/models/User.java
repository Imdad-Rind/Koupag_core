package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "User_Table")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID Id;
    @Column(unique = true)
    private String CNIC;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String password;
    @Transient
    private String userType;
    private LocalDateTime lastServed;
    private String name;
    @JsonBackReference
    @OneToOne(targetEntity = Address.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address")
    private Address address;


    @ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Roles> authorities;

    public User(String name, String phoneNumber, String email, String username, String password, Set<Roles> authorities) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public User(String name, String CNIC, String phoneNumber, String email,
                String password, String userType, LocalDateTime lastServed, Address address, Set<Roles> authorities) {
        this.name = name;
        this.lastServed = lastServed;
        this.address = address;
        this.CNIC = CNIC;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.authorities = authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    @Override
    public String getUsername() {
        return this.getCNIC();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
