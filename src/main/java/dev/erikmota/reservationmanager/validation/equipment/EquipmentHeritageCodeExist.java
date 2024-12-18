package dev.erikmota.reservationmanager.validation.equipment;

import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import dev.erikmota.reservationmanager.entities.Equipment;
import dev.erikmota.reservationmanager.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipmentHeritageCodeExist implements IValidations<Equipment> {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public void validate(Equipment data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.CREATE)) {
            if (data.getHeritageCode() != null && equipmentRepository.existsByHeritageCode(data.getHeritageCode())) {
                messagesToThrow.add(new Message(MessageEnum.HERITAGE_CODE_EXISTS));
            }
        }
        if (action.equals(ValidationActionsEnum.UPDATE)) {
            if (data.getHeritageCode() != null && equipmentRepository.existsByHeritageCode(data.getHeritageCode())) {
                if (!equipmentRepository.existsByHeritageCodeAndId(data.getHeritageCode(), data.getId())) {
                    messagesToThrow.add(new Message(MessageEnum.HERITAGE_CODE_EXISTS));
                }
            }
        }
    }
}
