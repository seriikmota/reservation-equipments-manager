package dev.erikmota.reservationmanager.dto.request;

import dev.erikmota.reservationmanager.entities.EquipmentState;
import dev.erikmota.reservationmanager.entities.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequestDTO {
    private String heritageCode;
    private String brand;
    private String model;
    private String description;
    private EquipmentType equipmentType;
    private EquipmentState state;
}
