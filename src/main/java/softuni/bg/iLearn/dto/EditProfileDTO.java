package softuni.bg.iLearn.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import softuni.bg.iLearn.model.enums.Gender;
import softuni.bg.iLearn.validation.ValidPassword;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileDTO {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Email
    private String confirmEmail;
    private String gender;
    @URL
    private String website;
    @URL
    private String twitter;
    @URL
    private String facebook;
    @URL
    private String instagram;
    private String headline;

}
