package dev.erikmota.reservationmanager.service.impl;

import dev.erikmota.reservationmanager.base.exception.BusinessException;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.service.impl.AbstractService;
import dev.erikmota.reservationmanager.dto.list.EquipmentListDTO;
import dev.erikmota.reservationmanager.dto.request.EquipmentRequestDTO;
import dev.erikmota.reservationmanager.dto.response.EquipmentResponseDTO;
import dev.erikmota.reservationmanager.entities.Equipment;
import dev.erikmota.reservationmanager.entities.EquipmentState;
import dev.erikmota.reservationmanager.mapper.EquipmentMapper;
import dev.erikmota.reservationmanager.repository.EquipmentRepository;
import dev.erikmota.reservationmanager.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentService extends AbstractService<EquipmentRequestDTO, EquipmentResponseDTO, EquipmentListDTO, Equipment, EquipmentRepository, EquipmentMapper, Long> implements IEquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    protected void prepareToCreate(Equipment data) {

    }

    @Override
    protected void prepareToUpdate(Equipment dataDB) {

    }

    @Override
    protected void prepareToDelete(Equipment dataDB) {

    }

    @Override
    public List<Equipment> findAvailableEquipments(String typeEquipment, Integer quantity, LocalDateTime startDate, LocalDateTime endDate) {
        List<Equipment> equipmentsAvailable = equipmentRepository.findAvailableEquipments(typeEquipment, startDate, endDate, PageRequest.of(0, quantity));

        if (equipmentsAvailable.isEmpty()){
            throw new BusinessException(MessageEnum.EQUIPMENT_TYPE_NOT_AVAILABLE, typeEquipment);
        } else if (quantity != equipmentsAvailable.size()) {
            throw new BusinessException(MessageEnum.QUANTITY_NOT_ENOUGH, equipmentsAvailable.size());
        }

        return equipmentsAvailable;
    }

    @Override
    public List<String> listTypesEquipment() {
        return equipmentRepository.findTypesEquipments();
    }

    @Override
    public List<String> listStatesEquipment() {
        return List.of(EquipmentState.USABLE.name(), EquipmentState.IN_MAINTENANCE.name(), EquipmentState.IN_REPAIR.name(), EquipmentState.DEFECTIVE.name(), EquipmentState.OBSOLETE.name());
    }
}
