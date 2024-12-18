package dev.erikmota.reservationmanager.repository;

import dev.erikmota.reservationmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByLoginAndId(String login, Long id);
    Boolean existsByEmailAndId(String email, Long id);
}
