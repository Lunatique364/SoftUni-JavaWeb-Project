package softuni.bg.iLearn.service.impl;

import org.springframework.stereotype.Service;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
