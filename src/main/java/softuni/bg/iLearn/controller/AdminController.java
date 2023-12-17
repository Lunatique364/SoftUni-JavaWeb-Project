package softuni.bg.iLearn.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import softuni.bg.iLearn.dto.DeleteUserDTO;
import softuni.bg.iLearn.dto.RegisterUserDTO;
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
    @ModelAttribute("deleteUserDTO")
    public DeleteUserDTO initUser() {
        return new DeleteUserDTO();
    }
    @GetMapping("/all-users")
    public String allUsersPage() {
        return "all-users";
    }

    @PostMapping("/user/{username}")
    public String postDelete(@PathVariable String username) {
       userService.deleteUserByUsername(username);
       return "redirect:/all-users";
    }

}
