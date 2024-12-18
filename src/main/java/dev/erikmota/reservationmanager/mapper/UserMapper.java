package dev.erikmota.reservationmanager.mapper;

import dev.erikmota.reservationmanager.base.mapper.GenericMapper;
import dev.erikmota.reservationmanager.dto.list.UserListDTO;
import dev.erikmota.reservationmanager.dto.request.UserRequestDTO;
import dev.erikmota.reservationmanager.dto.response.UserResponseDTO;
import dev.erikmota.reservationmanager.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper extends GenericMapper<UserRequestDTO, UserResponseDTO, UserListDTO, User, Long> {
}
