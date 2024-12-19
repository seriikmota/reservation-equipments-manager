package dev.erikmota.reservationmanager.base.exception;

import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.exception.message.MessageResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
public class BusinessException extends RuntimeException {
    private final MessageResponse messageResponse;

    public BusinessException(MessageResponse messageResponse){
        super();
        this.messageResponse = messageResponse;
    }

    public BusinessException(MessageEnum messageEnum, Object... args){
        super();
        messageResponse = new MessageResponse();
        messageResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        messageResponse.setMessages(new ArrayList<>());
        messageResponse.getMessages().add(new Message(messageEnum, args));
    }
}
