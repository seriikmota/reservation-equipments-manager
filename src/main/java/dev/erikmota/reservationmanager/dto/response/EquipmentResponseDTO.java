package dev.erikmota.reservationmanager.dto.response;

import dev.erikmota.reservationmanager.entities.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentResponseDTO {
    private Long id;
    private String heritageCode;
    private String brand;
    private String model;
    private String description;
    private String type;
    private EquipmentState state;
}
