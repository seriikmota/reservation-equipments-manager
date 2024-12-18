package dev.erikmota.reservationmanager.controller.impl;

import dev.erikmota.reservationmanager.base.controller.impl.AbstractController;
import dev.erikmota.reservationmanager.controller.IUserController;
import dev.erikmota.reservationmanager.dto.list.UserListDTO;
import dev.erikmota.reservationmanager.dto.request.UserRequestDTO;
import dev.erikmota.reservationmanager.dto.response.UserResponseDTO;
import dev.erikmota.reservationmanager.entities.User;
import dev.erikmota.reservationmanager.mapper.UserMapper;
import dev.erikmota.reservationmanager.service.impl.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController extends AbstractController<UserRequestDTO, UserResponseDTO, UserListDTO, User, UserService, UserMapper, Long> implements IUserController {
}
