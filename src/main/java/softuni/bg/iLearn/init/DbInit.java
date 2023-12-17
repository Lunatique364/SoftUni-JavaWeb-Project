package softuni.bg.iLearn.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.enums.Role;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.utils.CommonMessages;

import java.time.LocalDate;

@Component
public class DbInit implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final User admin = new User();
    private final User user = new User();

    @Autowired
    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.count() == 0) {
            admin.setUsername("admin");
            admin.setEmail(CommonMessages.EMAIL_ADMIN);
            admin.setPassword(passwordEncoder.encode(CommonMessages.EMAIL_PASSWORD));
            admin.setRole(Role.ADMIN);
            admin.setIsBanned(false);
            admin.setJoined(LocalDate.now());

            userRepository.save(admin);
        }

    }
}
