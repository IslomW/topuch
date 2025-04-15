package com.sharipov.topuch.application.converter;

import com.sharipov.topuch.application.dto.AddressDTO;
import com.sharipov.topuch.domain.entity.Address;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toDto (Address address);
    Address toEntity(AddressDTO addressDTO);
}
