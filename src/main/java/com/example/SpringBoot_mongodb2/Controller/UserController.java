package com.example.SpringBoot_mongodb2.Controller;

import com.example.SpringBoot_mongodb2.Entity.UserAuthentication;
import com.example.SpringBoot_mongodb2.Repository.UserRepository;
import com.example.SpringBoot_mongodb2.Entity.User;
import com.example.SpringBoot_mongodb2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public ResponseEntity<List<User>> getUserDetails(@PathVariable String username) {
        List<User> user = userService.getUser(username);

        if(!user.isEmpty()) return new ResponseEntity<>(user, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<List<User>> createUserDetails(@PathVariable String username, @RequestBody User user) {
        try {
            UserAuthentication user1 = userService.createUser(username, user);
            if(user1!=null) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}/{Id}")
    public ResponseEntity<User> updateUserDetails(@RequestBody User user, @PathVariable String username, @PathVariable String Id) {
        boolean status = userService.updateUser(username, user, Id);

        if(status) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}/{Id}")
    public ResponseEntity<?> deleteUserDetails(@PathVariable String username, @PathVariable String Id) {
        boolean status = userService.deleteUser(username, Id);

        if(status) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
