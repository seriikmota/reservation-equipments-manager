package dev.erikmota.reservationmanager.validation.reservation;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import dev.erikmota.reservationmanager.entities.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservationDateStart implements IValidations<Reservation> {

    @Override
    public void validate(Reservation data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.CREATE) || action.equals(ValidationActionsEnum.UPDATE)) {
            LocalDateTime now = LocalDateTime.now();
            if (data.getStartDate().isBefore(now.plusMinutes(5))) {
                messagesToThrow.add(new Message(MessageEnum.RESERVATION_DATE_START_BEFORE_NOW));
            }
            if (data.getEndDate().isBefore(data.getStartDate())) {
                messagesToThrow.add(new Message(MessageEnum.RESERVATION_DATE_END_BEFORE_START));
            } else if (data.getEndDate().isBefore(now.plusMinutes(5))) {
                messagesToThrow.add(new Message(MessageEnum.RESERVATION_DATE_END_BEFORE_NOW));
            }
        }
    }
}
