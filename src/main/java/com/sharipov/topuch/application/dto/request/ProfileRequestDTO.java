package com.sharipov.topuch.application.dto.request;

import com.sharipov.topuch.application.dto.AddressDTO;
import com.sharipov.topuch.domain.entity.Address;


public record ProfileRequestDTO(
        String first_name,
        String last_name,
        Long phoneNumber,
        String email,
        AddressDTO address
) {
}
