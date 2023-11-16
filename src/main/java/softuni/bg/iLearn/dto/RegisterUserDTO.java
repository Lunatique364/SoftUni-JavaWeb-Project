package softuni.bg.iLearn.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import softuni.bg.iLearn.validation.ValidPassword;

@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    @NotNull
    @Size(min = 3, max = 15)
    @NonNull
    private String username;
    @NotNull
    @Email
    @NonNull
    private String email;

    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
