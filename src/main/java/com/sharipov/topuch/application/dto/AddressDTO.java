package com.sharipov.topuch.application.dto;

public record AddressDTO(
        String countryCode,
        String state,
        String city,
        String  streetName,
        Integer streetNumber,
        String zipCode) {
}
