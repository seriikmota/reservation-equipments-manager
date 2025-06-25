package dev.erikmota.reservationmanager.base.controller.impl;

import dev.erikmota.reservationmanager.base.dto.CredentialDTO;
import dev.erikmota.reservationmanager.base.security.impl.CredentialProvider;

public abstract class CredentialController {

    protected CredentialDTO getCredential() {
        return (CredentialDTO) CredentialProvider.newInstance().getCurrentInstance();
    }

    protected Long getIdFromLoggedUser() {
        CredentialDTO credential = getCredential();
        return credential != null ? credential.getId() : null;
    }

    protected String getUserNameFromLoggedUser() {
        CredentialDTO credential = getCredential();
        return credential != null ? credential.getLogin() : null;
    }
}
