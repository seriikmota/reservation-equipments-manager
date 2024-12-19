package dev.erikmota.reservationmanager.validation.reservation;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import dev.erikmota.reservationmanager.entities.Reservation;
import dev.erikmota.reservationmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationUserExist implements IValidations<Reservation> {

    @Autowired
    private IUserService userService;

    @Override
    public void validate(Reservation data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.CREATE) || action.equals(ValidationActionsEnum.UPDATE)) {
            if (data.getUser() != null && data.getUser().getId() != null) {
                if (!userService.existsById(data.getUser().getId())) {
                    messagesToThrow.add(new Message(MessageEnum.USER_NOT_EXISTS));
                } else {
                    data.setUser(userService.getById(data.getUser().getId()));
                }
            }
        }
    }
}
