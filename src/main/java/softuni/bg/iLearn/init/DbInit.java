package softuni.bg.iLearn.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.bg.iLearn.model.Author;
import softuni.bg.iLearn.model.Course;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.enums.Category;
import softuni.bg.iLearn.model.enums.Role;
import softuni.bg.iLearn.repository.AuthorRepository;
import softuni.bg.iLearn.repository.CourseRepository;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.utils.CommonMessages;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DbInit implements ApplicationRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;
    private final User admin = new User();
    private final User user = new User();

    @Autowired
    public DbInit(UserRepository userRepository, CourseRepository courseRepository, AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {

        seedUsers();
    }

    @Transient
    private void seedCourses(User user){
        if (courseRepository.count() == 0) {

            Course course;
            Author author = new Author(user);
            authorRepository.save(author);

            for (int i = 0; i < 3; i++) {
                course = new Course();
                course.setName("Course" + i);
                author.getCourses().add(course);
                course.setAuthor(author);
                course.setDescription("Description" + i);
                course.setDateAdded(LocalDateTime.now());
                course.setCategory(Category.MATHEMATICS);
                course.setPrice(BigDecimal.valueOf(i * 10));
                courseRepository.save(course);
                authorRepository.save(author);
            }

        }

    }

    private void seedUsers(){
        if (userRepository.count() == 0) {
            admin.setUsername("admin");
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail(CommonMessages.EMAIL_ADMIN);
            admin.setPassword(passwordEncoder.encode(CommonMessages.EMAIL_PASSWORD));
            admin.setRole(Role.ADMIN);
            admin.setIsBanned(false);
            admin.setJoined(LocalDate.now());


            seedCourses(userRepository.save(admin));
        }

    }
}
