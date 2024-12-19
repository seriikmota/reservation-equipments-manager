package dev.erikmota.reservationmanager.controller;

import dev.erikmota.reservationmanager.base.controller.IAbstractController;
import dev.erikmota.reservationmanager.dto.list.ReservationListDTO;
import dev.erikmota.reservationmanager.dto.request.ReservationRequestDTO;
import dev.erikmota.reservationmanager.dto.response.ReservationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IReservationController extends IAbstractController<ReservationRequestDTO, ReservationResponseDTO, ReservationListDTO, Long> {

    @Operation(description = "Endpoint to confirm a reservation", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
    })
    ResponseEntity<ReservationResponseDTO> confirmReservation(@PathVariable Long id);

    @Operation(description = "Endpoint to cancel a reservation", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
    })
    ResponseEntity<ReservationResponseDTO> cancelReservation(@PathVariable Long id);
}
