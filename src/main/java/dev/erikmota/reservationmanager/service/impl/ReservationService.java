package dev.erikmota.reservationmanager.service.impl;

import dev.erikmota.reservationmanager.base.exception.BusinessException;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.service.impl.AbstractService;
import dev.erikmota.reservationmanager.dto.list.ReservationListDTO;
import dev.erikmota.reservationmanager.dto.request.ReservationRequestDTO;
import dev.erikmota.reservationmanager.dto.response.ReservationResponseDTO;
import dev.erikmota.reservationmanager.entities.Equipment;
import dev.erikmota.reservationmanager.entities.Reservation;
import dev.erikmota.reservationmanager.entities.ReservationStatus;
import dev.erikmota.reservationmanager.mapper.ReservationMapper;
import dev.erikmota.reservationmanager.repository.ReservationRepository;
import dev.erikmota.reservationmanager.service.IEquipmentService;
import dev.erikmota.reservationmanager.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService extends AbstractService<ReservationRequestDTO, ReservationResponseDTO, ReservationListDTO, Reservation, ReservationRepository, ReservationMapper, Long> implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private IEquipmentService equipmentService;

    @Override
    protected void prepareToCreate(Reservation data) {
        data.setReservationDate(LocalDateTime.now());
        data.setStatus(ReservationStatus.PENDING);
        List<Equipment> availableEquipments = equipmentService.findAvailableEquipments(data.getTypeEquipment(), data.getQuantity(), data.getStartDate(), data.getEndDate());
        data.setEquipments(availableEquipments);
    }

    @Override
    protected void prepareToUpdate(Reservation dataUpdate) {
        Reservation dataDB = this.validateIdModelExistsAndGet(dataUpdate.getId());
        alterReservationEquipments(dataUpdate, dataDB);
    }

    @Override
    protected void prepareToDelete(Reservation dataDB) {

    }

    @Override
    protected void validateToMapCreate(ReservationRequestDTO dtoCreate, List<Message> messagesToThrow) {
        super.validateToMapUpdate(dtoCreate, messagesToThrow);

        if ((dtoCreate.getQuantity() != null && dtoCreate.getQuantity() == 0)) {
            messagesToThrow.add(new Message(MessageEnum.QUANTITY_LESS_ZERO));
        }
    }

    @Override
    protected void validateToMapUpdate(ReservationRequestDTO dtoUpdate, List<Message> messagesToThrow) {
        super.validateToMapUpdate(dtoUpdate, messagesToThrow);

        if ((dtoUpdate.getQuantity() != null && dtoUpdate.getQuantity() == 0)) {
            messagesToThrow.add(new Message(MessageEnum.QUANTITY_LESS_ZERO));
        }
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void updateReservationStatus() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus = now.plusMinutes(10);

        List<Reservation> confirmedReservations = reservationRepository.findByStatusAndStartDateBetween(ReservationStatus.CONFIRMED, now, nowPlus);
        for (Reservation reservation : confirmedReservations) {
            if (reservation.getStartDate().isBefore(now) && reservation.getStatus() == ReservationStatus.CONFIRMED) {
                reservation.setStatus(ReservationStatus.IN_PROGRESS);
            }
        }

        List<Reservation> inProgressReservations = reservationRepository.findByStatusAndEndDateBetween(ReservationStatus.IN_PROGRESS, now, nowPlus);
        for (Reservation reservation : inProgressReservations) {
            if (reservation.getEndDate().isBefore(now) && reservation.getStatus() == ReservationStatus.IN_PROGRESS) {
                reservation.setStatus(ReservationStatus.FINISHED);
            }
        }

        List<Reservation> pendingReservations = reservationRepository.findByStatusAndStartDateBetween(ReservationStatus.PENDING, now, nowPlus);
        for (Reservation reservation : pendingReservations) {
            if (reservation.getStartDate().isBefore(now) && reservation.getStatus() == ReservationStatus.PENDING) {
                reservation.setStatus(ReservationStatus.EXPIRED);
            }
        }

        reservationRepository.saveAll(confirmedReservations);
        reservationRepository.saveAll(inProgressReservations);
        reservationRepository.saveAll(pendingReservations);
    }

    @Override
    public Reservation confirmReservation(Long id) {
        Reservation reservation = this.validateIdModelExistsAndGet(id);

        if (reservation.getStatus().equals(ReservationStatus.IN_PROGRESS))
            throw new BusinessException(MessageEnum.RESERVATION_CONFIRM_CANCEL, "confirmar", "em progresso");
        else if (reservation.getStatus().equals(ReservationStatus.FINISHED))
            throw new BusinessException(MessageEnum.RESERVATION_CONFIRM_CANCEL, "confirmar", "finalizada");
        else if (reservation.getStatus().equals(ReservationStatus.EXPIRED))
            throw new BusinessException(MessageEnum.RESERVATION_CONFIRM_CANCEL, "confirmar", "expirada");

        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation cancelReservation(Long id) {
        Reservation reservation = this.validateIdModelExistsAndGet(id);

        if (reservation.getStatus().equals(ReservationStatus.CONFIRMED))
            throw new BusinessException(MessageEnum.RESERVATION_CONFIRM_CANCEL, "cancelar", "confirmada");
        else if (reservation.getStatus().equals(ReservationStatus.FINISHED))
            throw new BusinessException(MessageEnum.RESERVATION_CONFIRM_CANCEL, "cancelar", "finalizada");
        else if (reservation.getStatus().equals(ReservationStatus.EXPIRED))
            throw new BusinessException(MessageEnum.RESERVATION_CONFIRM_CANCEL, "cancelar", "expirada");

        reservation.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.save(reservation);
    }

    private void alterReservationEquipments(Reservation dataUpdate, Reservation dataDB) {
        boolean quantityChanged = !Objects.equals(dataDB.getQuantity(), dataUpdate.getQuantity());
        boolean datesChanged = !dataDB.getStartDate().isEqual(dataUpdate.getStartDate()) || !dataDB.getEndDate().isEqual(dataUpdate.getEndDate());

        if (quantityChanged || datesChanged) {
            dataUpdate.setEquipments(new ArrayList<>(dataDB.getEquipments()));

            if (quantityChanged && !datesChanged) {
                int quantityDifference = dataUpdate.getQuantity() - dataDB.getQuantity();
                if (quantityDifference > 0) {
                    addEquipmentsInReservation(dataUpdate, quantityDifference);
                } else {
                    removeEquipmentsFromReservation(dataUpdate, Math.abs(quantityDifference));
                }
            }

            dataDB.setEquipments(null);

            if (datesChanged) {
                dataUpdate.setEquipments(null);
                addEquipmentsInReservation(dataUpdate, dataUpdate.getQuantity());
            }
        }
    }

    private void addEquipmentsInReservation(Reservation dataUpdate, int quantityAdd) {
        List<Equipment> availableEquipments = equipmentService.findAvailableEquipments(dataUpdate.getTypeEquipment(), quantityAdd, dataUpdate.getStartDate(), dataUpdate.getEndDate());
        dataUpdate.getEquipments().addAll(availableEquipments);
    }

    private void removeEquipmentsFromReservation(Reservation dataUpdate, int quantityRemove) {
        for (int i = 0; i < quantityRemove; i++) {
            dataUpdate.getEquipments().remove(dataUpdate.getEquipments().size() - 1);
        }
    }
}
