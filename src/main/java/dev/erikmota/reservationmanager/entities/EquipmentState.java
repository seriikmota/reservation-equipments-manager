package dev.erikmota.reservationmanager.entities;

import lombok.Getter;

@Getter
public enum EquipmentState {
    USABLE, IN_MAINTENANCE, IN_REPAIR, DEFECTIVE, OBSOLETE, RESERVED, AWAITING_RETURN
}
