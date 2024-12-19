package dev.erikmota.reservationmanager.dto.request;

import dev.erikmota.reservationmanager.base.annotations.MandatoryField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {

    @MandatoryField(name = "Tipo de Equipamento")
    private String typeEquipment;

    @MandatoryField(name = "Quantidade")
    private Integer quantity;

    @MandatoryField(name = "Data e Hora Inicial")
    private LocalDateTime startDate;

    @MandatoryField(name = "Data e Hora Final")
    private LocalDateTime endDate;

    @MandatoryField(name = "Usuário da Reserva")
    private Long userId;

    private String notes;
}
