package softuni.bg.iLearn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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



}
