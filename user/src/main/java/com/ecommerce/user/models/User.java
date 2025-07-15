package com.ecommerce.user.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "users")
public class User {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String phone;

    private UserRole role = UserRole.CUSTOMER;
    private Address address;

    @CreatedDate
    private LocalDate createdAt;
    @LastModifiedDate
    private LocalDate updatedAt;


}
