package dev.erikmota.reservationmanager.service;

import dev.erikmota.reservationmanager.base.service.IAbstractService;
import dev.erikmota.reservationmanager.dto.request.EquipmentRequestDTO;
import dev.erikmota.reservationmanager.entities.Equipment;

import java.util.List;

public interface IEquipmentService extends IAbstractService<EquipmentRequestDTO, Equipment, Long> {
    List<String> listTypesEquipment();
    List<String> listStatesEquipment();
}
