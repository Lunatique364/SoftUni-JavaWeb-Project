package softuni.bg.iLearn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.bg.iLearn.model.Course;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weekly_newsletter")
public class WeeklyNewsletter {
    @Id
    private Long id;
    @OneToMany
    private List<Course> courses;
    @DateTimeFormat
    private LocalDateTime date;


}
