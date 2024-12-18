package dev.erikmota.reservationmanager.base.exception;

import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import lombok.Getter;

@Getter
public class ParameterRequiredException extends RuntimeException {
    private final MessageEnum messageEnum;

    public ParameterRequiredException(String message) {
        super(MessageEnum.PARAMETER_REQUIRED.getCode() + message);
        this.messageEnum = MessageEnum.PARAMETER_REQUIRED;
    }
}
