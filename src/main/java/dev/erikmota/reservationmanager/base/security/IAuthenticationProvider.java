package dev.erikmota.reservationmanager.base.security;

public interface IAuthenticationProvider {
    Credential getAuthentication(final String token);
}
