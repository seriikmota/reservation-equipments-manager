package dev.erikmota.reservationmanager.entities;

import lombok.Getter;

@Getter
public enum EquipmentState {
    USABLE(1, "Disponível"),
    IN_MAINTENANCE(2, "Em manutenção"),
    IN_REPAIR(3, "Em repação"),
    DEFECTIVE(4, "Com defeito"),
    OBSOLETE(5, "Obsoleto"),
    RESERVED(6, "Reservado"),
    AWAITING_RETURN(7, "Aguardando Retorno");

    EquipmentState(Integer code, String description) {
    }
}
