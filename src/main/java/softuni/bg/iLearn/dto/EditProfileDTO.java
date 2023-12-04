package softuni.bg.iLearn.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.bg.iLearn.model.enums.Gender;
import softuni.bg.iLearn.validation.UniqueUsername;
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
    @ValidPassword
    private String password;
    @ValidPassword
    private String confirmPassword;
    private Gender gender;
    private String website;
    private String twitter;
    private String facebook;
    private String instagram;
    private String headline;

}
