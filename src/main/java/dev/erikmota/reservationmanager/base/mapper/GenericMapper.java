package dev.erikmota.reservationmanager.base.mapper;

import dev.erikmota.reservationmanager.base.entities.GenericModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

public interface GenericMapper<DTORequest, DTOResponse, DTOList, MODEL extends GenericModel<TYPE_PK>, TYPE_PK> {
    MODEL toModel(DTORequest dto);
    DTOResponse toDTO(MODEL model);

    void updateModelFromModel(@MappingTarget MODEL entity, MODEL updateEntity);

    @Named(value = "toDTOList")
    DTOList toDTOList(MODEL model);

    @IterableMapping(qualifiedByName = "toDTOList")
    List<DTOList> toDtoList(List<MODEL> modelList);
}
