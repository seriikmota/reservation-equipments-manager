package dev.erikmota.reservationmanager.base.exception.message;

import lombok.Getter;

@Getter
public enum MessageEnum {
    GENERAL("ME001", Message.MessageType.ERROR),
    MANDATORY_FIELD("ME002", Message.MessageType.ERROR),
    PARAMETER_REQUIRED("ME003", Message.MessageType.ERROR),
    NOT_FOUND("ME004", Message.MessageType.ERROR),
    EMAIL_INVALID("ME005", Message.MessageType.ERROR);

    private final String code;
    private final Message.MessageType type;

    MessageEnum(String code, Message.MessageType type){
        this.code = code;
        this.type = type;
    }
}
