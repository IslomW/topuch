package com.sharipov.topuch.application.converter;

import com.sharipov.topuch.application.dto.request.ProfileRequestDTO;
import com.sharipov.topuch.application.dto.response.ProfileResponseDTO;
import com.sharipov.topuch.domain.entity.Profile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface ProfileMapper {

    ProfileRequestDTO toDto(Profile profile);

    Profile toEntity(ProfileRequestDTO profileRequestDTO);

    List<ProfileResponseDTO> toDtoList(List<Profile> profiles);

}
