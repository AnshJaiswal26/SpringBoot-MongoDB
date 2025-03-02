package com.example.SpringBoot_mongodb2.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String department;
}
