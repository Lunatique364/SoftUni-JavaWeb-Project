package softuni.bg.iLearn.service;


import softuni.bg.iLearn.dto.RegisterUserDTO;

public interface UserService {

    boolean register(RegisterUserDTO registerUserDTO);
}
