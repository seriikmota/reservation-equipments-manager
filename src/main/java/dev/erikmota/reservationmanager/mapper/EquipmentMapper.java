package dev.erikmota.reservationmanager.mapper;

import dev.erikmota.reservationmanager.base.mapper.GenericMapper;
import dev.erikmota.reservationmanager.dto.list.EquipmentListDTO;
import dev.erikmota.reservationmanager.dto.request.EquipmentRequestDTO;
import dev.erikmota.reservationmanager.dto.response.EquipmentResponseDTO;
import dev.erikmota.reservationmanager.entities.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EquipmentMapper extends GenericMapper<EquipmentRequestDTO, EquipmentResponseDTO, EquipmentListDTO, Equipment, Long> {
}
