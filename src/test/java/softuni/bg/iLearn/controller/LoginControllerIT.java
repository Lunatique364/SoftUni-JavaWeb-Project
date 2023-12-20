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
import softuni.bg.iLearn.model.MailDetails;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.utils.CommonMessages;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class LoginControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Test
    public void testResetPassword() throws Exception {

        User user = userRepository.findByUsername("user123").orElse(null);

        String beforePassword = user.getPassword();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/forgot-password")
                        .param("email", "user123@ilearn.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        User updatedPassword = userRepository.findByUsername("user123").orElse(null);

        Assertions.assertNotEquals(beforePassword, updatedPassword);

    }
}
