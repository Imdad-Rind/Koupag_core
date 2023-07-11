package com.Koupag.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/hi")
    public ResponseEntity<String> hello(@RequestHeader String token){
        return new ResponseEntity<>("Hello, ur token is: "+token, HttpStatus.OK);
    }
}
