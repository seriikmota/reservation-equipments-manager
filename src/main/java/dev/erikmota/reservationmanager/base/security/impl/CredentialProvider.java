package dev.erikmota.reservationmanager.base.security.impl;

import dev.erikmota.reservationmanager.base.security.Credential;
import dev.erikmota.reservationmanager.base.security.ICredentialProvider;
import org.springframework.security.core.context.SecurityContextHolder;

public class CredentialProvider implements ICredentialProvider {

    public static CredentialProvider newInstance() {
        return new CredentialProvider();
    }

    @Override
    public Credential getCurrentInstance() {
        return (Credential) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
