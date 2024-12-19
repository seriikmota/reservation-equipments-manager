package dev.erikmota.reservationmanager.repository;

import dev.erikmota.reservationmanager.entities.Equipment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Boolean existsByHeritageCode(String heritageCode);
    Boolean existsByHeritageCodeAndId(String heritageCode, Long id);

    @Query("SELECT DISTINCT e.type FROM Equipment e")
    List<String> findTypesEquipments();

    @Query("""
        SELECT e FROM Equipment e
        WHERE e.state = dev.erikmota.reservationmanager.entities.EquipmentState.USABLE
        AND e.type = :typeEquipment
        AND e.id NOT IN (
            SELECT e.id FROM Reservation r
            JOIN r.equipments eq
            WHERE eq.id = e.id
            AND r.status NOT IN (dev.erikmota.reservationmanager.entities.ReservationStatus.CANCELLED)
            AND r.startDate <= :endDate
            AND r.endDate >= :startDate
        )
""")
    List<Equipment> findAvailableEquipments(@Param("typeEquipment") String typeEquipment, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
}
