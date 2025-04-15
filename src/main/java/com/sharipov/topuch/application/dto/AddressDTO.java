package com.sharipov.topuch.application.dto;

public record AddressDTO(
        String country_code,
        String state,
        String city,
        String  street_name,
        Integer street_number,
        String zip_code) {
}
