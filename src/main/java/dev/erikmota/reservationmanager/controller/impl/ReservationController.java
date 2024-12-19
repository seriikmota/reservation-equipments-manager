package dev.erikmota.reservationmanager.controller.impl;

import dev.erikmota.reservationmanager.base.controller.impl.AbstractController;
import dev.erikmota.reservationmanager.controller.IReservationController;
import dev.erikmota.reservationmanager.dto.list.ReservationListDTO;
import dev.erikmota.reservationmanager.dto.request.ReservationRequestDTO;
import dev.erikmota.reservationmanager.dto.response.ReservationResponseDTO;
import dev.erikmota.reservationmanager.entities.Reservation;
import dev.erikmota.reservationmanager.mapper.ReservationMapper;
import dev.erikmota.reservationmanager.service.impl.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController extends AbstractController<ReservationRequestDTO, ReservationResponseDTO, ReservationListDTO, Reservation, ReservationService, ReservationMapper, Long> implements IReservationController {

    @PatchMapping(path = "/{id}/confirm")
    @Transactional
    public ResponseEntity<ReservationResponseDTO> confirmReservation(@PathVariable Long id) {
        ReservationResponseDTO modelSaved = mapper.toDTO(service.confirmReservation(id));
        return ResponseEntity.ok(modelSaved);
    }

    @PatchMapping(path = "/{id}/cancel")
    @Transactional
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@PathVariable Long id) {
        ReservationResponseDTO modelSaved = mapper.toDTO(service.cancelReservation(id));
        return ResponseEntity.ok(modelSaved);
    }
}
