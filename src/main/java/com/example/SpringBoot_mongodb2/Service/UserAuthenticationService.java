package com.example.SpringBoot_mongodb2.Service;

import com.example.SpringBoot_mongodb2.Entity.UserAuthentication;
import com.example.SpringBoot_mongodb2.Repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public ResponseEntity<String> register(UserAuthentication userAuthentication) {
        UserAuthentication status = userAuthenticationRepository.findByUsername(String.valueOf(userAuthentication.getUsername()));

        if(status==null) {
            if(!userAuthentication.getPassword().equals(userAuthentication.getConfirmPassword()))
                return new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
            userAuthenticationRepository.save(userAuthentication);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }

    public ResponseEntity<String> changePassword(UserAuthentication userAuthentication) {
        UserAuthentication status = userAuthenticationRepository.findByUsername(userAuthentication.getUsername());

        if(status != null) {
            if(userAuthentication.getPassword().equals(status.getPassword())) {

                status.setPassword(userAuthentication.getNewPassword());
                status.setConfirmPassword(userAuthentication.getNewPassword());
                status.setNewPassword(userAuthentication.getNewPassword());
                userAuthenticationRepository.save(status);

                return new ResponseEntity<>(HttpStatus.ACCEPTED);

            } else return new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);

        }
        return new ResponseEntity<>("User not registered", HttpStatus.NOT_FOUND);
    }


}
