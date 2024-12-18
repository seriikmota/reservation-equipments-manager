package dev.erikmota.reservationmanager.dto.request;

import dev.erikmota.reservationmanager.entities.EquipmentType;
import dev.erikmota.reservationmanager.entities.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {
    private EquipmentType typeEquipment;
    private Integer quantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime reservationDate;
    private String notes;
    private ReservationStatus status;
    private Long userId;
    private List<Long> equipmentsId;
}