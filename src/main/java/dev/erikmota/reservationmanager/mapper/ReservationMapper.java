package dev.erikmota.reservationmanager.mapper;

import dev.erikmota.reservationmanager.base.mapper.GenericMapper;
import dev.erikmota.reservationmanager.dto.list.ReservationListDTO;
import dev.erikmota.reservationmanager.dto.request.ReservationRequestDTO;
import dev.erikmota.reservationmanager.dto.response.ReservationResponseDTO;
import dev.erikmota.reservationmanager.entities.Reservation;
import dev.erikmota.reservationmanager.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ReservationMapper extends GenericMapper<ReservationRequestDTO, ReservationResponseDTO, ReservationListDTO, Reservation, Long> {

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    Reservation toModel(ReservationRequestDTO dto);

    @Named(value = "toDTOList")
    @Mapping(target = "user", expression = "java(mapUserToString(reservation.getUser()))")
    List<ReservationListDTO> toDtoList(List<Reservation> modelList);

    default String mapUserToString(User user) {
        return user != null ? user.getName() : null;
    }

    @Named("mapUserIdToUser")
    default User mapUserIdToUser(Long userId) {
        return userId == null ? null : User.builder().id(userId).build();
    }

}
