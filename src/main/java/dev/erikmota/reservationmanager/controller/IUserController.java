package dev.erikmota.reservationmanager.controller;

import dev.erikmota.reservationmanager.base.controller.IAbstractController;
import dev.erikmota.reservationmanager.dto.list.UserListDTO;
import dev.erikmota.reservationmanager.dto.request.UserRequestDTO;
import dev.erikmota.reservationmanager.dto.response.UserResponseDTO;

public interface IUserController extends IAbstractController<UserRequestDTO, UserResponseDTO, UserListDTO, Long> {
}
