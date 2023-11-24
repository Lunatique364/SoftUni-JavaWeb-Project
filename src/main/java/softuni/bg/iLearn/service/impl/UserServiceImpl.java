package softuni.bg.iLearn.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.dto.RegisterUserDTO;
import softuni.bg.iLearn.model.MailDetails;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.enums.Role;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.service.UserService;
import softuni.bg.iLearn.utils.CommonMessages;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ModelMapper modelMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean register(RegisterUserDTO registerUserDTO) {

        User user = modelMapper.map(registerUserDTO, User.class);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(Role.REGULAR));

        MailDetails mailDetails = new MailDetails(CommonMessages.EMAIL_SENDER, user.getEmail(), CommonMessages.EMAIL_CREATION_SUBJECT ,String.format(CommonMessages.EMAIL_CREATION_BODY, user.getUsername()));

//        TODO fix NoSuchMethodEx while sending email
//        mailService.sendMail(mailDetails);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean isUniqueUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }




}
