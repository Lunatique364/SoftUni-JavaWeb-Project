package softuni.bg.iLearn.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.bg.iLearn.model.enums.Gender;
import softuni.bg.iLearn.model.enums.Role;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany
    private List<Course> courses;

    @Column()
    private String website;
    @Column()
    private String twitter;
    @Column()
    private String facebook;
    @Column()
    private String instagram;
    @Column()
    private String headline;
    @DateTimeFormat
    @Column()
    private LocalDate joined;

    @Column
    private boolean isBanned;
}
