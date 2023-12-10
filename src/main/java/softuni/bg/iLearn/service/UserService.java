package softuni.bg.iLearn.service;


import org.springframework.security.core.userdetails.UserDetails;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.dto.RegisterUserDTO;
import softuni.bg.iLearn.dto.ResetPasswordDTO;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.view.ProfileView;

public interface UserService {

    boolean register(RegisterUserDTO registerUserDTO);

    boolean isUniqueUsername(String username);

    ProfileView getProfileView(UserDetails userDetails);

    boolean editProfile(EditProfileDTO editProfileDTO, String username);
    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);

    boolean deleteUserById(String id);

    ProfileView getProfileView(String id);
}
