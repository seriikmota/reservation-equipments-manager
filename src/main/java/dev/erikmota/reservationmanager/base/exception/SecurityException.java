package dev.erikmota.reservationmanager.base.exception;

import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.exception.message.MessageResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
public class SecurityException extends RuntimeException {
    private final MessageResponse messageResponse;

    public SecurityException(MessageResponse messageResponse){
        super();
        this.messageResponse = messageResponse;
    }

    public SecurityException(MessageEnum error){
        super();
        messageResponse = new MessageResponse();
        messageResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        messageResponse.setMessages(new ArrayList<>());
        messageResponse.getMessages().add(new Message(error));
    }

    public SecurityException(MessageEnum error, String... params){
        super();
        messageResponse = new MessageResponse();
        messageResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        messageResponse.setMessages(new ArrayList<>());
        messageResponse.getMessages().add(new Message(error, params));
    }

    public SecurityException(MessageEnum error, HttpStatus status){
        super();
        messageResponse = new MessageResponse();
        messageResponse.setStatusCode(status.value());
        messageResponse.setMessages(new ArrayList<>());
        messageResponse.getMessages().add(new Message(error));
    }
}
