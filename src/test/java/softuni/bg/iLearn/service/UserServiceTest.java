package softuni.bg.iLearn.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import softuni.bg.iLearn.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

//    @Test
//    void testGetProfileView() {
//        // Given
//        String username = "testUser";
//        UserDetails userDetails = new User(username, "password", "ROLE_REGULAR");
//
//        // Mock the UserRepository to return a user when findByUsername is called
//        UserEntity mockUser = new UserEntity();  // Replace with your actual UserEntity class
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//
//        // When
//        ProfileView result = yourServiceClass.getProfileView(userDetails);
//
//        // Then
//        // Verify that userRepository.findByUsername is called once with the correct argument
//        verify(userRepository, times(1)).findByUsername(username);
//
//        // Verify that the result is not null
//        assertEquals(mockUser.getUsername(), result.getUsername());
//        // You might need to assert other fields or conditions depending on your implementation
//    }
//
//    @Test
//    void testGetProfileViewUserNotFound() {
//        // Given
//        String username = "nonexistentUser";
//        UserDetails userDetails = new User(username, "password", null);
//
//        // Mock the UserRepository to return an empty optional when findByUsername is called
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//
//        // When/Then
//        // Verify that UsernameNotFoundException is thrown with the correct message
//        UsernameNotFoundException exception = assertThrows(
//                UsernameNotFoundException.class,
//                () -> yourServiceClass.getProfileView(userDetails)
//        );
//        assertEquals(String.format(CommonMessages.USER_DOESNT_EXIST, username), exception.getMessage());
//
//        // Verify that userRepository.findByUsername is called once with the correct argument
//        verify(userRepository, times(1)).findByUsername(username);
//    }
}
