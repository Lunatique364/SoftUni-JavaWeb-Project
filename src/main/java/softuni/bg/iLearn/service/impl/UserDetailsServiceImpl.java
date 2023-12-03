package softuni.bg.iLearn.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.enums.Role;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.utils.CommonMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(CommonMessages.USER_DOESNT_EXIST, username)));

    }

    private UserDetails map(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Stream.of(user.getRole()).map(this::map).collect(Collectors.toList())
        );

    }

    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.name()
        );
    }


}
