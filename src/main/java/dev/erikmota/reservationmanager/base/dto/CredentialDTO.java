package dev.erikmota.reservationmanager.base.dto;

import dev.erikmota.reservationmanager.base.security.Credential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDTO implements Credential {
    private Long id;
    private String name;
    private String login;
    private String email;
    private List<String> roles;
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private Long refreshExpiresIn;
    private boolean activeState;
    private String password;
}
