package com.Koupag.Controller;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/auth/")
public class AuthController {

    /*private UserService userService;
    private RolesService rolesService;
    private PasswordEncoder encoder;
    private UserMapper mapper;
    private AuthenticationManager authenticationManager;*/
    private AuthenticationService authenticationService;

    public AuthController( AuthenticationService authenticationService/*UserService userService, RolesService rolesService,
                          PasswordEncoder encoder, UserMapper mapper,
                          AuthenticationManager authenticationManager*/) {
       /* this.userService = userService;
        this.rolesService = rolesService;
        this.encoder = encoder;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;*/
        this.authenticationService = authenticationService;
    }
    @PostMapping("/api/auth/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        /*if (userService.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("user already exits", HttpStatus.BAD_REQUEST);
        }

        UserModel user = new UserModel();
        user.setUsername(registerDTO.getUsername());
        user.setUsername(encoder.encode(registerDTO.getPassword()));

        Roles role = rolesService.getByName("USER").get();
        user.setRoles(Collections.singletonList(role));

        userService.creteNewUser(mapper.mayToDTO(user));*/
        authenticationService.registerUser(registerDTO);


        return new ResponseEntity<>("User register success!",HttpStatus.OK);

    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto){
        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);*/
        authenticationService.loginUser(loginDto);

        return new ResponseEntity<>("login Success!", HttpStatus.OK);
    }
}
