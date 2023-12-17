package softuni.bg.iLearn.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.dto.RegisterUserDTO;
import softuni.bg.iLearn.dto.ResetPasswordDTO;
import softuni.bg.iLearn.exception.NoAuthoritiesException;
import softuni.bg.iLearn.model.MailDetails;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.enums.Gender;
import softuni.bg.iLearn.model.enums.Role;
import softuni.bg.iLearn.model.view.ProfileView;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.service.UserService;
import softuni.bg.iLearn.utils.CommonMessages;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;


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
        user.setRole(Role.REGULAR);
        user.setJoined(LocalDate.now());
        user.setIsBanned(false);
        MailDetails mailDetails = new MailDetails(CommonMessages.EMAIL_SENDER, user.getEmail(), CommonMessages.EMAIL_CREATION_SUBJECT, String.format(CommonMessages.EMAIL_CREATION_BODY, user.getUsername()));

        mailService.sendRegistrationMail(mailDetails);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean isUniqueUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public ProfileView getProfileView(UserDetails userDetails) {
        return userRepository
                .findByUsername(userDetails.getUsername())
                .map(this::toProfileView)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(CommonMessages.USER_DOESNT_EXIST, userDetails.getUsername())));
    }

    @Override
    public boolean editProfile(EditProfileDTO editProfileDTO, String value, UserDetails userDetails) {
        User user;

        if (!value.contains("@")) {
            user = userRepository.findByUsername(value).get();
        } else {
            user = userRepository.findByEmail(value).get();
        }

        if (value.equals(userDetails.getUsername()) || userDetails.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {

            editUser(user, editProfileDTO);
            userRepository.save(user);
            return true;
        }
        throw new NoAuthoritiesException();
    }

    @Override
    public boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {

        User user = userRepository.findByEmail(resetPasswordDTO.getEmail()).orElse(null);

        if (user == null) {
            return false;
        }

        String newPassword = getNewRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        MailDetails mailDetails = new MailDetails(CommonMessages.EMAIL_SENDER, user.getEmail(), CommonMessages.RESET_EMAIL_SUBJECT, String.format(CommonMessages.RESET_EMAIL_BODY, newPassword));

        mailService.sendResetPasswordMail(mailDetails);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUserByUsername(String username) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void banUserByUsername(String username) {

    }

    @Override
    public ProfileView getProfileView(String username) {
        return toProfileView(userRepository.findByUsername(username).get());
    }

    private String getNewRandomPassword() {
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(33, 45);
        return specialChars.map(n -> (char) n).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void editUser(User user, EditProfileDTO editProfileDTO) {
        //TODO think of better way

        user.setFirstName(editProfileDTO.getFirstName().isEmpty() ? user.getFirstName() : editProfileDTO.getFirstName());
        user.setLastName(editProfileDTO.getLastName().isEmpty() ? user.getLastName() : editProfileDTO.getLastName());
        user.setEmail(editProfileDTO.getEmail().isEmpty() ? user.getEmail() : editProfileDTO.getEmail());
        user.setGender(editProfileDTO.getGender().isEmpty() ? Gender.valueOf(user.getGender().name()) : Gender.valueOf(editProfileDTO.getGender().toUpperCase()));
        user.setWebsite(editProfileDTO.getWebsite().isEmpty() ? user.getWebsite() : editProfileDTO.getWebsite());
        user.setTwitter(editProfileDTO.getTwitter().isEmpty() ? user.getTwitter() : editProfileDTO.getTwitter());
        user.setFacebook(editProfileDTO.getFacebook().isEmpty() ? user.getFacebook() : editProfileDTO.getFacebook());
        user.setInstagram(editProfileDTO.getInstagram().isEmpty() ? user.getInstagram() : editProfileDTO.getInstagram());
        user.setHeadline(editProfileDTO.getHeadline().isEmpty() ? user.getHeadline() : editProfileDTO.getHeadline());

    }


    private ProfileView toProfileView(User user) {
        return ProfileView.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .courses(user.getCourses())
                .website(user.getWebsite())
                .twitter(user.getTwitter())
                .instagram(user.getInstagram())
                .facebook(user.getFacebook())
                .headline(user.getHeadline())
                .build();
    }


}
