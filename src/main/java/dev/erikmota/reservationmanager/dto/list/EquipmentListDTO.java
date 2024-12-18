package dev.erikmota.reservationmanager.dto.list;

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
public class EquipmentListDTO {
    private Long id;
    private EquipmentType equipmentType;
    private EquipmentState state;
    private String heritageCode;
    private String brand;
    private String model;
    private Boolean available;
}
