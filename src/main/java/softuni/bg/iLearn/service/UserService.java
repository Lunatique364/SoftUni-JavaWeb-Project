package softuni.bg.iLearn.service;


import org.springframework.security.core.userdetails.UserDetails;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.dto.RegisterUserDTO;
import softuni.bg.iLearn.dto.ResetPasswordDTO;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.view.ProfileView;

import java.util.Optional;

public interface UserService {

    boolean register(RegisterUserDTO registerUserDTO);

    boolean isUniqueUsername(String username);

    ProfileView getProfileView(UserDetails userDetails);

    boolean editProfile(EditProfileDTO editProfileDTO, String username, UserDetails userDetails);
    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);

    ProfileView getProfileView(String username);

    boolean deleteUserByUsername(String username);

    Optional<User> findByUsername(String username);
}

