package com.sharipov.topuch.application.dto.request;

import com.sharipov.topuch.application.dto.AddressDTO;
import com.sharipov.topuch.domain.entity.Address;


public record ProfileRequestDTO(
        String firstName,
        String lastName,
        Long phoneNumber,
        String email,
        AddressDTO address
) {
}
