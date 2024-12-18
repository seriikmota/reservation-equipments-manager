package dev.erikmota.reservationmanager.controller.impl;

import dev.erikmota.reservationmanager.base.controller.impl.AbstractController;
import dev.erikmota.reservationmanager.controller.IEquipmentController;
import dev.erikmota.reservationmanager.dto.list.EquipmentListDTO;
import dev.erikmota.reservationmanager.dto.request.EquipmentRequestDTO;
import dev.erikmota.reservationmanager.dto.response.EquipmentResponseDTO;
import dev.erikmota.reservationmanager.entities.Equipment;
import dev.erikmota.reservationmanager.mapper.EquipmentMapper;
import dev.erikmota.reservationmanager.service.IEquipmentService;
import dev.erikmota.reservationmanager.service.impl.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/equipment")
public class EquipmentController extends AbstractController<EquipmentRequestDTO, EquipmentResponseDTO, EquipmentListDTO, Equipment, EquipmentService, EquipmentMapper, Long> implements IEquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping("/getTypesEquipment")
    public ResponseEntity<List<String>> listTypesEquipment(){
        List<String> listTypes = equipmentService.listTypesEquipment();
        return ResponseEntity.ok(listTypes);
    }

    @GetMapping("/getStatesEquipment")
    public ResponseEntity<List<String>> listStatesEquipment(){
        List<String> listStates = equipmentService.listStatesEquipment();
        return ResponseEntity.ok(listStates);
    }
}
