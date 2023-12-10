package softuni.bg.iLearn.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.model.view.AllUsersView;
import softuni.bg.iLearn.model.view.ProfileView;
import softuni.bg.iLearn.service.AdminService;
import softuni.bg.iLearn.service.UserService;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @ModelAttribute("allUsersView")
    public AllUsersView initUsers() {
        return new AllUsersView(adminService.getAllUsers());
    }
    @GetMapping("/all-users")
    public String allUsersPage() {
        return "all-users";
    }

    @GetMapping("/user/{id}")
    public String viewProfile(@PathVariable String id, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {

        ProfileView profileView = userService.getProfileView(id);
        model.addAttribute("profileView", profileView);
        return "profile";
    }

    @GetMapping("/user/{id}/edit-profile")
    public String editProfile(@PathVariable String id, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {

        ProfileView profileView = userService.getProfileView(userDetails);
        model.addAttribute("profileView", profileView);
        return "profile";
    }

    @PostMapping("/user/{id}")
    public String postDelete(@PathVariable String id) {
       userService.deleteUserById(id);
       return "redirect:/all-users";
    }

    @PostMapping("/user/{id}/edit-profile")
    public String postEdit(@Valid EditProfileDTO editProfileDTO,
                           @PathVariable String id,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userService.editProfile(editProfileDTO, editProfileDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("editProfileDTO", editProfileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editProfileDTO", bindingResult);
            return "redirect:/edit-profile";
        }

        return "redirect:/my-profile";
    }
}
