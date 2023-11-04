package com.Koupag.security_configs;

import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String cnic) throws UsernameNotFoundException {
        User user = userService.getUserByCNIC(cnic).orElseThrow(() -> new UsernameNotFoundException("CNIC not found"));
        return new org.springframework.security.core.userdetails.User(user.getCNIC(),user.getPassword(),mapRolesToAuthorities((Set<Roles>) user.getAuthorities()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Set<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toSet());
    }
}
