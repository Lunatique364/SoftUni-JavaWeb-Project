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

@Component
public class DbInit implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${spring.mail.properties.mail.credentials.admin-username}")
//    private static String ADMIN_EMAIL;
//
//    @Value("${spring.mail.properties.mail.credentials.admin-password}")
//    private static String ADMIN_PASSWORD;
    private final User admin = new User();

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
            userRepository.save(admin);
        }

    }
}
