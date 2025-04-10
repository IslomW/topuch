package com.sharipov.topuch.entity;

import lombok.Getter;
import lombok.Setter;

public class Profile {
    private Long id;
    private String first_name;
    private String last_name;
    private String signup_ts;
    private Long phoneNumber;
    private String email;
    private Address address;
}
