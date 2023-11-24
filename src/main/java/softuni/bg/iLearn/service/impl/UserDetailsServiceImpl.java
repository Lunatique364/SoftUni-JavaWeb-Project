package softuni.bg.iLearn.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.enums.Role;
import softuni.bg.iLearn.repository.UserRepository;

import java.util.List;


public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::parseUser)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s doesn't exist!", username)));

    }

    private UserDetails parseUser(User user) {

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of()) // TODO add roles
                .build();
    }

    private static GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.name()
        );
    }


}
