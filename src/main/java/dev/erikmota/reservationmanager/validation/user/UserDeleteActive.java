package dev.erikmota.reservationmanager.validation.user;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import dev.erikmota.reservationmanager.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDeleteActive implements IValidations<User> {

    @Override
    public void validate(User data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.DELETE)) {
            if (data.getActive()) {
                messagesToThrow.add(new Message(MessageEnum.DELETE_USER_ACTIVE));
            }
        }
    }
}
