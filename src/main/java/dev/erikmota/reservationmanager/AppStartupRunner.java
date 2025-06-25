package dev.erikmota.reservationmanager;

import dev.erikmota.reservationmanager.entities.User;
import dev.erikmota.reservationmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private UserRepository userRepository;

    public void initData() {
        if (userRepository.count() == 0) {
            User user = User.builder()
                    .login("admin")
                    .password("$2y$10$1MgdNcIduZBhvlTym.PKje0nDX54UVS28jTa2U3lB3JvrqAj4fAdq") // Senha == admin
                    .name("Administrador")
                    .email("teste@gmail.com")
                    .registrationCode("1234567890")
                    .active(Boolean.TRUE)
                    .build();

            userRepository.save(user);
        }
    }

    public void run(ApplicationArguments args) {
        try {
            this.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
