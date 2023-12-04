package softuni.bg.iLearn.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import softuni.bg.iLearn.model.enums.Gender;
import softuni.bg.iLearn.validation.UniqueUsername;
import softuni.bg.iLearn.validation.ValidPassword;

@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    @NotNull
    @Size(min = 3, max = 15)
    @NonNull
    @UniqueUsername
    private String username;
    @NotNull
    @Email
    @NonNull
    private String email;

    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;

    private Gender gender;



}
