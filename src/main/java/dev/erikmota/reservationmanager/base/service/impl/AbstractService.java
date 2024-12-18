package dev.erikmota.reservationmanager.base.service.impl;

import dev.erikmota.reservationmanager.base.entities.GenericModel;
import dev.erikmota.reservationmanager.base.enums.ValidationActionsEnum;
import dev.erikmota.reservationmanager.base.exception.BusinessException;
import dev.erikmota.reservationmanager.base.exception.DataException;
import dev.erikmota.reservationmanager.base.exception.ParameterRequiredException;
import dev.erikmota.reservationmanager.base.exception.message.Message;
import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;
import dev.erikmota.reservationmanager.base.exception.message.MessageResponse;
import dev.erikmota.reservationmanager.base.mapper.GenericMapper;
import dev.erikmota.reservationmanager.base.service.IAbstractService;
import dev.erikmota.reservationmanager.base.util.ReflectionUtils;
import dev.erikmota.reservationmanager.base.validations.IValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.*;

public abstract class AbstractService<DTORequest, DTOResponse, DTOList, MODEL extends GenericModel<TYPE_PK>, REPOSITORY extends JpaRepository<MODEL, TYPE_PK>,
        MAPPER extends GenericMapper<DTORequest, DTOResponse, DTOList, MODEL, TYPE_PK>, TYPE_PK> implements IAbstractService<DTORequest, MODEL, TYPE_PK> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private REPOSITORY repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MAPPER mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired(required = false)
    private List<IValidations<MODEL>> validations = new ArrayList<>();

    public List<MODEL> listAll() {
        return repository.findAll();
    }

    public Page<MODEL> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public MODEL getById(TYPE_PK id){
        return this.validateIdModelExistsAndGet(id);
    }

    public MODEL create(DTORequest dtoCreate) {
        List<Message> messagesToThrow = new ArrayList<>();

        validateMandatoryFieldsDTO(dtoCreate, messagesToThrow);
        prepareToMapCreate(dtoCreate);
        validateToMapCreate(dtoCreate, messagesToThrow);

        throwMessages(messagesToThrow);

        MODEL data = mapper.toModel(dtoCreate);
        return this.create(data);
    }

    protected MODEL create(MODEL dataCreate) {
        List<Message> messagesToThrow = new ArrayList<>();
        prepareToCreate(dataCreate);

        validateBusinessLogicForInsert(dataCreate, messagesToThrow);

        throwMessages(messagesToThrow);
        return repository.save(dataCreate);
    }

    public MODEL update(TYPE_PK id, DTORequest dtoUpdate) {
        List<Message> messagesToThrow = new ArrayList<>();

        validateMandatoryFieldsDTO(dtoUpdate, messagesToThrow);
        prepareToMapUpdate(dtoUpdate);
        validateToMapUpdate(dtoUpdate, messagesToThrow);

        throwMessages(messagesToThrow);

        MODEL dataUpdate = mapper.toModel(dtoUpdate);
        return this.update(id, dataUpdate);
    }

    protected MODEL update(TYPE_PK id, MODEL dataUpdate) {
        List<Message> messagesToThrow = new ArrayList<>();
        var dataDB = validateIdModelExistsAndGet(id);
        dataUpdate.setId(id);

        prepareToUpdate(dataUpdate);
        validateBusinessLogicForUpdate(dataUpdate, messagesToThrow);

        throwMessages(messagesToThrow);

        mapper.updateModelFromModel(dataDB, dataUpdate);
        return repository.save(dataDB);
    }

    public MODEL deleteById(TYPE_PK id){
        List<Message> messagesToThrow = new ArrayList<>();

        MODEL dataToRemove = this.validateIdModelExistsAndGet(id);

        validateBusinessLogicForDelete(dataToRemove, messagesToThrow);

        throwMessages(messagesToThrow);

        prepareToDelete(dataToRemove);
        this.repository.delete(dataToRemove);
        return dataToRemove;
    }

    public MODEL validateIdModelExistsAndGet(TYPE_PK id){
        if (!Objects.nonNull(id)) throw new ParameterRequiredException("id");

        Optional<MODEL> byId = repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new DataException(MessageEnum.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    protected void throwMessages(List<Message> messagesToThrow) {
        if (!messagesToThrow.isEmpty()) {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setMessages(messagesToThrow);
            messageResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            throw new BusinessException(messageResponse);
        }
    }

    protected void validateBusinessLogicForInsert(MODEL data, List<Message> messagesToThrow) {
        validations.forEach(v -> v.validate(data, ValidationActionsEnum.CREATE, messagesToThrow));
    }

    protected void validateBusinessLogicForUpdate(MODEL data, List<Message> messagesToThrow) {
        validations.forEach(v -> v.validate(data, ValidationActionsEnum.UPDATE, messagesToThrow));
    }

    protected void validateBusinessLogicForDelete(MODEL data, List<Message> messagesToThrow) {
        validations.forEach(v -> v.validate(data, ValidationActionsEnum.DELETE, messagesToThrow));
    }

    private void validateMandatoryFieldsDTO(DTORequest dtoCreate, List<Message> messagesToThrow) {
        List<String> fieldsInvalid = new ArrayList<>();
        ReflectionUtils.validateMandatoryFields(dtoCreate, fieldsInvalid);

        if (!fieldsInvalid.isEmpty()) {
            for (String field : fieldsInvalid) {
                messagesToThrow.add(new Message(MessageEnum.MANDATORY_FIELD, field));
            }
        }
    }

    private void validateAnnotations(Object object, List<Message> messagesToThrow) {
        Map<String, List<MessageEnum>> mapMessageEnums = ReflectionUtils.validateAnnotations(object);
        if (!mapMessageEnums.isEmpty()) {
            for (String fieldKey : mapMessageEnums.keySet()) {
                for (MessageEnum messageEnum : mapMessageEnums.get(fieldKey)) {
                    messagesToThrow.add(new Message(messageEnum, fieldKey));
                }
            }
        }
    }

    protected void prepareToMapCreate(DTORequest dto) {}
    protected void validateToMapCreate(DTORequest dto, List<Message> messagesToThrow) {
        this.validateAnnotations(dto, messagesToThrow);
    }
    protected void prepareToMapUpdate(DTORequest dto) {}
    protected void validateToMapUpdate(DTORequest dto, List<Message> messagesToThrow) {
        this.validateAnnotations(dto, messagesToThrow);
    }

    protected abstract void prepareToCreate(MODEL data);
    protected abstract void prepareToUpdate(MODEL dataDB);
    protected abstract void prepareToDelete(MODEL dataDB);
}
