package com.example.SpringBoot_mongodb2.Controller;

import com.example.SpringBoot_mongodb2.Entity.UserAuthentication;
import com.example.SpringBoot_mongodb2.Service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAuthentication userAuthentication) {
        return userAuthenticationService.register(userAuthentication);
    }

    @PutMapping("/changePass")
    public ResponseEntity<String> changePass(@RequestBody UserAuthentication userAuthentication) {
        return userAuthenticationService.changePassword(userAuthentication);
    }

}
