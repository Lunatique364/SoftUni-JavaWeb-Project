package softuni.bg.iLearn.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.bg.iLearn.dto.ContactDTO;
import softuni.bg.iLearn.service.MailService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ContactControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void restReceiveContact() throws Exception {

        ContactDTO contactDTO = new ContactDTO("Stefan", "stefan.stefanov@gmail.com", "subject", "message me back");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/contact")
                        .param("sender", "Stefan")
                        .param("email", "user123456@ilearn.com")
                        .param("subject", "subject")
                        .param("message", "new message")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertDoesNotThrow(() -> mailService.receiveContact(contactDTO));
    }
}
