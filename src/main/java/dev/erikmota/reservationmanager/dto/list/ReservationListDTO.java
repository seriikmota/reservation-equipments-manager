package dev.erikmota.reservationmanager.dto.list;

import dev.erikmota.reservationmanager.entities.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationListDTO {
    private Long id;
    private String user;
    private String typeEquipment;
    private Integer quantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime reservationDate;
    private ReservationStatus status;
}
