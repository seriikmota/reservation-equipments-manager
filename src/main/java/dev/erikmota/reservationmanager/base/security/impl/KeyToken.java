package dev.erikmota.reservationmanager.base.security.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyToken {

    @Value("${api.security.jwt.secret:defaultSecretKey}")
    private String secretKey;

    @Getter
    @Value("${api.security.jwt.issuer:ReservationManager}")
    private String issuer;

    public KeyToken() {}

    public KeyToken(final String secretKey, final String issuer) {
        this.issuer = issuer;
        this.secretKey = secretKey;
    }

    public byte[] getSecretKey() {
        return secretKey.getBytes();
    }

}
