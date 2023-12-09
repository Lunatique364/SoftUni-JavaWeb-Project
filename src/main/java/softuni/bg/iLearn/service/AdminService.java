package softuni.bg.iLearn.service;

import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.view.ProfileView;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    ProfileView getProfileView();
}

