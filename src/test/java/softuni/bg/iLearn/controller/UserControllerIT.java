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
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    @WithMockUser(username="userAdmin", roles = {"ADMIN"})
    public void shootRegisterUser() throws Exception {

//        int before = (int) userRepository.count();
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/register").param("username", "user12345")
                        .param("email", "user12345@ilearn.com")
                        .param("password", "1234")
                        .param("confirmPassword", "1234")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

//        int after = (int) userRepository.count();
//        Assertions.assertEquals(before + 1, after);
    }
}
