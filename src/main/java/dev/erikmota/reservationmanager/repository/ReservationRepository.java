package dev.erikmota.reservationmanager.repository;

import dev.erikmota.reservationmanager.entities.Reservation;
import dev.erikmota.reservationmanager.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r.typeEquipment FROM Reservation r WHERE r.id = :id")
    String findTypeEquipmentById(@Param("id") Long id);

    List<Reservation> findByStatusAndStartDateBetween(ReservationStatus status, LocalDateTime start, LocalDateTime end);

    List<Reservation> findByStatusAndEndDateBetween(ReservationStatus status, LocalDateTime start, LocalDateTime end);
}
