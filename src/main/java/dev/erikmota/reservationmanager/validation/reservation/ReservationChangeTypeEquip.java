package dev.erikmota.reservationmanager.validation.reservation;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import dev.erikmota.reservationmanager.entities.Reservation;
import dev.erikmota.reservationmanager.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationChangeTypeEquip implements IValidations<Reservation> {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void validate(Reservation data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.UPDATE)) {
            if (!data.getTypeEquipment().equals(reservationRepository.findTypeEquipmentById(data.getId()))) {
                messagesToThrow.add(new Message(MessageEnum.RESERVATION_CHANGE_TYPE));
            }
        }
    }
}
