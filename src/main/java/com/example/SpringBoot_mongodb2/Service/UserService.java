package com.example.SpringBoot_mongodb2.Service;

import com.example.SpringBoot_mongodb2.Entity.User;
import com.example.SpringBoot_mongodb2.Entity.UserAuthentication;
import com.example.SpringBoot_mongodb2.Repository.UserAuthenticationRepository;
import com.example.SpringBoot_mongodb2.Repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public List<User> getUser(String username) {
        UserAuthentication user = userAuthenticationRepository.findByUsername(username);

        if(user!=null){
            return user.getUserDetails();
        }
        return Collections.emptyList();
    }

    @Transactional
    public UserAuthentication createUser(String username, User user) {
        UserAuthentication status = userAuthenticationRepository.findByUsername(username);

        try{
            if (status!=null) {
                userRepository.save(user);
                status.getUserDetails().add(user);
//                status.setUsername(null);
                userAuthenticationRepository.save(status);
                return status;
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurs "+e);
        }

        return null;
    }

    public boolean updateUser(String username, User newUser, String Id) {
        UserAuthentication status = userAuthenticationRepository.findByUsername(username);
        Optional<User> op = userRepository.findById(Id);
        User oldUser = null;

        if (op.isPresent()) {
            oldUser = op.get();
            newUser.setId(oldUser.getId());
        }

        if(status!=null){
            userRepository.save(newUser);
            status.getUserDetails().remove(oldUser);
            status.getUserDetails().add(newUser);
            userAuthenticationRepository.save(status);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String username, String Id) {
        UserAuthentication toUpdate = userAuthenticationRepository.findByUsername(username);
        Optional<User> op = userRepository.findById(Id);
        User toDelete = null;
        
        if(op.isPresent()) {
            toDelete= op.get();
        }
        if(toUpdate!=null){
            userRepository.deleteById(Id);
            toUpdate.getUserDetails().remove(toDelete);
            userAuthenticationRepository.save(toUpdate);
            return true;
        }
        return false;
    }
}
