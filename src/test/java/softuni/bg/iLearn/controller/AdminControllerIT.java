package softuni.bg.iLearn.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.bg.iLearn.exception.UserNotFoundException;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AdminControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateUserRole() throws Exception {
        User user = userRepository.findByUsername("user123").orElse(null);

        String beforeRole = user.getRole().name();
        String newRole = beforeRole.equals("REGULAR") ? "LECTURER" : "REGULAR";


        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/update-role/user123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        User updatedUser = userRepository.findByUsername("user123").orElse(null);

        Assertions.assertEquals(updatedUser.getRole().name(), newRole);

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteUser() throws Exception {
        User user = userRepository.findByUsername("user123").orElse(null);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/delete/user123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        User updatedUser = userRepository.findByUsername("user123").orElse(null);

        Assertions.assertEquals(updatedUser, null);

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testBanUser() throws Exception {
        boolean isBanned = userRepository.findByUsername("user123").get().getIsBanned();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/ban/user123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        boolean updatedIsBanned = userRepository.findByUsername("user123").get().getIsBanned();

        Assertions.assertEquals(updatedIsBanned, true);

    }
}
