package dev.erikmota.reservationmanager.base.util;

import dev.erikmota.reservationmanager.base.exception.message.MessageEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {

    public static String trim(String str) {
        return str != null ? str.trim() : null;
    }

    public static List<MessageEnum> validateEmail(String email) {
        List<MessageEnum> errors = new ArrayList<>();
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (email != null) {
            if (!Pattern.matches(emailRegex, email)) {
                errors.add(MessageEnum.EMAIL_INVALID);
            }
        }

        return errors;
    }
}
