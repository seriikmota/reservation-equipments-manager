package dev.erikmota.reservationmanager.repository;

import dev.erikmota.reservationmanager.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Boolean existsByHeritageCode(String heritageCode);
    Boolean existsByHeritageCodeAndId(String heritageCode, Long id);

    @Query("SELECT DISTINCT e.type FROM Equipment e")
    List<String> findTypesEquipments();
}
