package dev.erikmota.reservationmanager.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAbstractService<DTORequest, MODEL, TYPE_PK> {
    List<MODEL> listAll();
    Page<MODEL> listAll(Pageable pageable);
    MODEL create(DTORequest dtoCreate);
    MODEL update(TYPE_PK id, DTORequest dtoUpdate);
    MODEL getById(TYPE_PK id);
    MODEL deleteById(TYPE_PK id);
}
