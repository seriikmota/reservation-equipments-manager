package dev.erikmota.reservationmanager.service.impl;

import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.service.impl.AbstractService;
import dev.erikmota.reservationmanager.base.util.Utils;
import dev.erikmota.reservationmanager.dto.list.UserListDTO;
import dev.erikmota.reservationmanager.dto.request.UserRequestDTO;
import dev.erikmota.reservationmanager.dto.response.UserResponseDTO;
import dev.erikmota.reservationmanager.entities.User;
import dev.erikmota.reservationmanager.mapper.UserMapper;
import dev.erikmota.reservationmanager.repository.UserRepository;
import dev.erikmota.reservationmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<UserRequestDTO, UserResponseDTO, UserListDTO, User, UserRepository, UserMapper, Long> implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void prepareToCreate(User data) {
        data.setPassword(Utils.encryptPassword(data.getPassword()));
    }

    @Override
    protected void prepareToUpdate(User dataUpdate) {
        dataUpdate.setPassword(Utils.encryptPassword(dataUpdate.getPassword()));
    }

    @Override
    protected void prepareToDelete(User dataDB) {

    }

    @Override
    protected void prepareToMapCreate(UserRequestDTO dtoCreate) {
        dtoCreate.setLogin(Utils.trim(dtoCreate.getLogin()));
        dtoCreate.setPassword(Utils.trim(dtoCreate.getPassword()));
        dtoCreate.setConfirmPassword(Utils.trim(dtoCreate.getConfirmPassword()));
    }

    @Override
    protected void validateToMapCreate(UserRequestDTO dtoCreate, List<Message> messagesToThrow) {
        super.validateToMapCreate(dtoCreate, messagesToThrow);

        if (dtoCreate.getPassword() == null) {
            messagesToThrow.add(new Message(MessageEnum.MANDATORY_FIELD, "Senha"));
        }
        if (!Utils.comparePasswords(dtoCreate.getPassword(), dtoCreate.getConfirmPassword())) {
            messagesToThrow.add(new Message(MessageEnum.PASSWORDS_DIFFERENT));
        }
    }

    @Override
    protected void validateToMapUpdate(UserRequestDTO dtoUpdate, List<Message> messagesToThrow) {
        super.validateToMapUpdate(dtoUpdate, messagesToThrow);

        if ((dtoUpdate.getPassword() != null || dtoUpdate.getConfirmPassword() != null) && !Utils.comparePasswords(dtoUpdate.getPassword(), dtoUpdate.getConfirmPassword())) {
            messagesToThrow.add(new Message(MessageEnum.PASSWORDS_DIFFERENT));
        }
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
