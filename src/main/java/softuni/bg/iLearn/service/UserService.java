package softuni.bg.iLearn.service;


import org.springframework.security.core.userdetails.UserDetails;
import softuni.bg.iLearn.dto.RegisterUserDTO;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.view.ProfileView;

public interface UserService {

    boolean register(RegisterUserDTO registerUserDTO);

    boolean isUniqueUsername(String username);

    ProfileView getProfileView(UserDetails userDetails);
}
