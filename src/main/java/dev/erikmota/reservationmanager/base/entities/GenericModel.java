package dev.erikmota.reservationmanager.base.entities;

public interface GenericModel<TYPE_PK> {
    TYPE_PK getId();
    void setId(TYPE_PK id);
}