package dev.erikmota.reservationmanager.validation.user;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import dev.erikmota.reservationmanager.entities.User;
import dev.erikmota.reservationmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserExistLogin implements IValidations<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(User data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.CREATE)) {
            if (data.getLogin() != null && userRepository.existsByLogin(data.getLogin())) {
                messagesToThrow.add(new Message(MessageEnum.LOGIN_EXISTS));
            }
        }
        if (action.equals(ValidationActionsEnum.UPDATE)) {
            if (data.getLogin() != null && userRepository.existsByLogin(data.getLogin())) {
                if (!userRepository.existsByLoginAndId(data.getLogin(), data.getId())) {
                    messagesToThrow.add(new Message(MessageEnum.LOGIN_EXISTS));
                }
            }
        }
    }
}
