package dev.erikmota.reservationmanager.service;

import dev.erikmota.reservationmanager.base.service.IAbstractService;
import dev.erikmota.reservationmanager.dto.request.UserRequestDTO;
import dev.erikmota.reservationmanager.entities.User;

public interface IUserService extends IAbstractService<UserRequestDTO, User, Long> {
    Boolean existsById(Long id);
}
