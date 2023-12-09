package softuni.bg.iLearn.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.iLearn.dto.ResetPasswordDTO;
import softuni.bg.iLearn.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("badCredentials", true);
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String postRegister(@Valid ResetPasswordDTO resetPasswordDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userService.resetPassword(resetPasswordDTO)) {
            redirectAttributes.addFlashAttribute("resetPasswordDTO", resetPasswordDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resetPasswordDTO", bindingResult);
            redirectAttributes.addFlashAttribute("error", "invalid e-mail address");
            return "redirect:/forgot-password";
        }


        return "redirect:/login";
    }
}
