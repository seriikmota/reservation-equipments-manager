package dev.erikmota.reservationmanager.dto.request;

import dev.erikmota.reservationmanager.base.annotations.EmailValidate;
import dev.erikmota.reservationmanager.base.annotations.MandatoryField;
import dev.erikmota.reservationmanager.base.annotations.PasswordValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @MandatoryField(name = "Login")
    private String login;

    @PasswordValidate(name = "Senha")
    private String password;
    private String confirmPassword;

    @MandatoryField(name = "Nome")
    private String name;

    @MandatoryField(name = "Email")
    @EmailValidate(name = "Email")
    private String email;

    @MandatoryField(name = "Código de Registro")
    private String registrationCode;

    @MandatoryField(name = "Status")
    private Boolean active;
}
