package softuni.bg.iLearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author")
    private List<Course> courses;

    public Author(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.courses = new ArrayList<>();
    }
}
