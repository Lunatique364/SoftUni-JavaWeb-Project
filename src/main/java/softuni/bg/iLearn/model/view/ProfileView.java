package softuni.bg.iLearn.model.view;

import jakarta.persistence.Column;
import lombok.*;
import softuni.bg.iLearn.model.Course;
import softuni.bg.iLearn.model.enums.Gender;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileView {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private List<Course> courses;
    private String website;
    private String twitter;
    private String youtube;
    private String facebook;
    private String instagram;
    private String headline;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
