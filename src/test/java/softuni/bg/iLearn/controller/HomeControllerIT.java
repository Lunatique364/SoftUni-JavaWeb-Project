package softuni.bg.iLearn.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.bg.iLearn.model.Course;
import softuni.bg.iLearn.model.Subscription;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.WeeklyNewsletter;
import softuni.bg.iLearn.repository.CourseRepository;
import softuni.bg.iLearn.repository.SubscriptionRepository;
import softuni.bg.iLearn.repository.UserRepository;
import softuni.bg.iLearn.repository.WeeklyNewsletterRepository;
import softuni.bg.iLearn.service.MailService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private WeeklyNewsletterRepository weeklyNewsletterRepository;

    @Autowired
    private MailService mailService;

    @Test
    public void testRegisterNewsletter() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .param("email", "user123@ilearn.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Subscription byEmail = subscriptionRepository.findByEmail("user123@ilearn.com").orElse(null);

        Assertions.assertNotEquals(byEmail, null);

    }

    @Test
    void testHomePage() throws Exception {
        // Perform a GET request to the home page
        mockMvc.perform(MockMvcRequestBuilders. get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testAboutUsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("about-us"));
    }
}
