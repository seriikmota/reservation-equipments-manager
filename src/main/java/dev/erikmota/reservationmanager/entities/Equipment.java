package dev.erikmota.reservationmanager.entities;

import dev.erikmota.reservationmanager.base.entities.GenericModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long id;

    @Column(nullable = false)
    private String heritageCode;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EquipmentType type;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EquipmentState state;

    @Column(nullable = false)
    private Boolean available;
}

