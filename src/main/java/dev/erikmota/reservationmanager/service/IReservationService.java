package dev.erikmota.reservationmanager.service;

import dev.erikmota.reservationmanager.base.service.IAbstractService;
import dev.erikmota.reservationmanager.dto.request.ReservationRequestDTO;
import dev.erikmota.reservationmanager.entities.Reservation;

public interface IReservationService extends IAbstractService<ReservationRequestDTO, Reservation, Long> {
    Reservation confirmReservation(Long id);
    Reservation cancelReservation(Long id);
}
