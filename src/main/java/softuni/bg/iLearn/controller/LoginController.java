package softuni.bg.iLearn.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import softuni.bg.iLearn.dto.RegisterUserDTO;

@Controller
public class LoginController {

    public LoginController() {
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO initUser() {
        return new RegisterUserDTO();
    }





}
