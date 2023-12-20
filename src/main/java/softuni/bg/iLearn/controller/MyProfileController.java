package softuni.bg.iLearn.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.exception.UserNotFoundException;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.model.view.ProfileView;
import softuni.bg.iLearn.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class MyProfileController {

    private final UserService userService;

    @Autowired
    public MyProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit-profile/{username}")
    public String editProfile(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable String username) {

        if (userService.findByUsername(username).isEmpty()) { // TODO MOVE TO SERVICE
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
        if (isUserPresent(username)) {  // TODO MOVE TO SERVICE
            model.addAttribute("username", username);
            ProfileView profileView = userService.getProfileView(username);
            model.addAttribute("profileView", profileView);
            return "profile";
        }
        return "redirect:/";
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

    private boolean isUserPresent(String username) { // TODO: move to service SOMEHOW
        Optional<User> user = this.userService.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        return true;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView onProfileNotFound(UserNotFoundException unfe) {
        ModelAndView modelAndView = new ModelAndView("error/user-not-found");
        modelAndView.addObject("username", unfe.getUsername());
        return modelAndView;
    }

}
