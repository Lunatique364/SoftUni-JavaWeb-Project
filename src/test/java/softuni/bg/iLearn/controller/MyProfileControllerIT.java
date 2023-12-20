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
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MyProfileControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shootEditUser() throws Exception {
        User user = userRepository.findByUsername("admin").orElse(null);

        String beforeEmail = user.getEmail();
        String beforeFirstName = user.getFirstName();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/edit-profile/admin")
                        .param("email", "admin@admin.com")
                        .param("confirmEmail", "admin@admin.com")
                        .param("firstName", "new name")
                        .param("lastName", "new last name")
                        .param("website", "")
                        .param("twitter", "")
                        .param("facebook", "")
                        .param("instagram", "")
                        .param("gender", "Male")
                        .param("headline", "hello")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        User updatedAdmin = userRepository.findByUsername("admin").orElse(null);

        Assertions.assertNotEquals(beforeEmail, updatedAdmin.getEmail());
        Assertions.assertNotEquals(beforeFirstName, updatedAdmin.getFirstName());

    }

    @Test
    void testUserNotFoundExceptionHandler() throws Exception {
        String nonExistentUsername = "user124";

        mockMvc.perform(MockMvcRequestBuilders.get("/edit-profile/" + nonExistentUsername))
                .andExpect(status().is3xxRedirection());
    }
}
