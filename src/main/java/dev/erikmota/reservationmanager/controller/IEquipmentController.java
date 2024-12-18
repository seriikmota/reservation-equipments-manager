package dev.erikmota.reservationmanager.controller;

import dev.erikmota.reservationmanager.base.controller.IAbstractController;
import dev.erikmota.reservationmanager.dto.list.EquipmentListDTO;
import dev.erikmota.reservationmanager.dto.request.EquipmentRequestDTO;
import dev.erikmota.reservationmanager.dto.response.EquipmentResponseDTO;

public interface IEquipmentController extends IAbstractController<EquipmentRequestDTO, EquipmentResponseDTO, EquipmentListDTO, Long> {
}
