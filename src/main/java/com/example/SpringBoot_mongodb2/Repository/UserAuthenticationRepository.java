package com.example.SpringBoot_mongodb2.Repository;

import com.example.SpringBoot_mongodb2.Entity.UserAuthentication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuthenticationRepository extends MongoRepository<UserAuthentication, String> {
    UserAuthentication findByUsername(String username);
}
