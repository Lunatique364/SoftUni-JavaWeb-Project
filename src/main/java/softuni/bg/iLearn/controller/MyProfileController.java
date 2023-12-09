package softuni.bg.iLearn.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.model.view.ProfileView;
import softuni.bg.iLearn.service.UserService;

@Controller
public class MyProfileController {

    private final UserService userService;

    @Autowired
    public MyProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-profile")
    public String myProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ProfileView profileView = userService.getProfileView(userDetails);
        model.addAttribute("profileView", profileView);
        return "my-profile";
    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ProfileView profileView = userService.getProfileView(userDetails);
        model.addAttribute("editProfileView", profileView);
        return "edit-profile";
    }

    @ModelAttribute("editProfileDTO")
    public EditProfileDTO initUser() {
        return new EditProfileDTO();
    }


    @PostMapping("/edit-profile")
    public String postEdit(@Valid EditProfileDTO editProfileDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors() || !userService.editProfile(editProfileDTO, userDetails.getUsername())) {
            redirectAttributes.addFlashAttribute("editProfileDTO", editProfileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editProfileDTO", bindingResult);
            return "redirect:/edit-profile";
        }

        return "redirect:/my-profile";
    }

}
