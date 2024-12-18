package dev.erikmota.reservationmanager.base.controller.impl;

import dev.erikmota.reservationmanager.base.controller.IAbstractController;
import dev.erikmota.reservationmanager.base.entities.GenericModel;
import dev.erikmota.reservationmanager.base.mapper.GenericMapper;
import dev.erikmota.reservationmanager.base.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<DTORequest, DTOResponse, DTOList, MODEL extends GenericModel<TYPE_PK>, SERVICE extends IAbstractService<DTORequest, MODEL, TYPE_PK>, MAPPER extends GenericMapper<DTORequest, DTOResponse, DTOList, MODEL, TYPE_PK>, TYPE_PK>
        implements IAbstractController<DTORequest, DTOResponse, DTOList, TYPE_PK> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected SERVICE service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MAPPER mapper;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOResponse> create(@RequestBody DTORequest dtoCreate){
        DTOResponse resultDTO = mapper.toDTO(service.create(dtoCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<DTOResponse> update(@PathVariable TYPE_PK id, @RequestBody DTORequest dto) {
        DTOResponse modelSaved = mapper.toDTO(service.update(id, dto));
        return ResponseEntity.ok(modelSaved);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<DTOResponse> delete(@PathVariable TYPE_PK id){
        DTOResponse deleteDTO = mapper.toDTO(service.deleteById(id));
        return ResponseEntity.ok(deleteDTO);
    }

    @GetMapping
    public ResponseEntity<List<DTOList>> listAll(){
        List<DTOList> listDTO = mapper.toDtoList(service.listAll());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Page<DTOList>> listAllPagination(Pageable pageable){
        Page<DTOList> listDTO = service.listAll(pageable).map(obj -> mapper.toDTOList(obj));
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DTOResponse> getById(@PathVariable TYPE_PK id){
        DTOResponse dtoResult = mapper.toDTO(service.getById(id));
        return ResponseEntity.ok(dtoResult);
    }
}
