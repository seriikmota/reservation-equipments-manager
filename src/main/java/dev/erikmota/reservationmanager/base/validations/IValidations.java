package dev.erikmota.reservationmanager.base.validations;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;

import java.util.List;

public interface IValidations<MODEL> {
    void validate(MODEL data, ValidationActionsEnum action, List<Message> messagesToThrow);
}
