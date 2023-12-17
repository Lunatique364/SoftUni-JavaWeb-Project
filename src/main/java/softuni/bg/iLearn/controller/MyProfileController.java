package softuni.bg.iLearn.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.exception.UserNotFoundException;
import softuni.bg.iLearn.model.view.ProfileView;
import softuni.bg.iLearn.service.UserService;

import java.util.List;

@Controller
public class MyProfileController {

    private final UserService userService;

    @Autowired
    public MyProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit-profile/{username}")
    public String editProfile(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable String username) {

        if (userService.findByUsername(username).isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        model.addAttribute("username", username);
        ProfileView profileView = userService.getProfileView(userDetails);
        model.addAttribute("editProfileView", profileView);
        return "edit-profile";

    }

    @GetMapping("/user/{username}")
    public String viewProfile( Model model,
                               @AuthenticationPrincipal UserDetails userDetails, @PathVariable String username) {
        model.addAttribute("username", username);
        ProfileView profileView = userService.getProfileView(username);
        model.addAttribute("profileView", profileView);
        return "profile";
    }

    @ModelAttribute("editProfileDTO")
    public EditProfileDTO initUser() {
        return new EditProfileDTO();
    }


    @PostMapping("/edit-profile/{username}")
    public String postEdit(@Valid EditProfileDTO editProfileDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails, @PathVariable String username) {

        if (bindingResult.hasErrors() || !userService.editProfile(editProfileDTO, username, userDetails)) {
            redirectAttributes.addFlashAttribute("editProfileDTO", editProfileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editProfileDTO", bindingResult);
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                redirectAttributes.addFlashAttribute(error.getField() + "Error", error.getDefaultMessage());
            }

            return "redirect:/edit-profile/{username}";
        }

        return "redirect:/user/{username}";

    }

}
