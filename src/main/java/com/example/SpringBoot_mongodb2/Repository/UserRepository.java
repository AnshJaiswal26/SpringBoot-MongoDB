package com.example.SpringBoot_mongodb2.Repository;

import com.example.SpringBoot_mongodb2.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
