package dev.erikmota.reservationmanager.service.impl;

import dev.erikmota.reservationmanager.base.dto.CredentialDTO;
import dev.erikmota.reservationmanager.base.exception.BusinessException;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.service.IUserProviderService;
import dev.erikmota.reservationmanager.entities.User;
import dev.erikmota.reservationmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProviderService implements IUserProviderService {

    @Autowired
    private UserRepository repository;

    @Override
    public CredentialDTO getCredentialByLogin(String login) {
        User user = repository.findByLogin(login);
        if (user == null) throw new BusinessException(MessageEnum.LOGIN_INVALID);
        return getCredential(user);
    }

    @Override
    public CredentialDTO getCredentialByEmail(String email) {
        User user = repository.findByEmail(email);
        if (user == null) throw new BusinessException(MessageEnum.LOGIN_INVALID);
        return getCredential(user);
    }

    public CredentialDTO getCredential(User user) {
        List<String> roles = new ArrayList<>();

        return CredentialDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(roles)
                .activeState(user.getActive())
                .build();
    }
}
