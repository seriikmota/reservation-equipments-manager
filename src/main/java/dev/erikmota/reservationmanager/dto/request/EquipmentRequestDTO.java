package dev.erikmota.reservationmanager.dto.request;

import dev.erikmota.reservationmanager.base.annotations.MandatoryField;
import dev.erikmota.reservationmanager.entities.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequestDTO {

    @MandatoryField(name = "Código de Patrimônio")
    private String heritageCode;

    @MandatoryField(name = "Marca")
    private String brand;

    @MandatoryField(name = "Modelo")
    private String model;

    private String description;

    @MandatoryField(name = "Tipo de Equipamento")
    private String type;

    @MandatoryField(name = "Status do Equipamento")
    private EquipmentState state;
}
