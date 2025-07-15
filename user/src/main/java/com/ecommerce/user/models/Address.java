package com.ecommerce.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Address {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}
