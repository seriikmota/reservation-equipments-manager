package dev.erikmota.reservationmanager.base.service;

import dev.erikmota.reservationmanager.base.dto.CredentialDTO;

public interface IUserProviderService {
    CredentialDTO getCredentialByLogin(String username);
    CredentialDTO getCredentialByEmail(String email);
}
