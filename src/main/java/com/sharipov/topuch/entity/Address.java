package com.sharipov.topuch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String country_code;
    private String state;
    private String city;
    private String  street_name;
    private Integer street_number;
    private String zip_code;
}
