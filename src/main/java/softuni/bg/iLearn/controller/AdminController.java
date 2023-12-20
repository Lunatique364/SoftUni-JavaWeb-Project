package softuni.bg.iLearn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.bg.iLearn.model.view.AllUsersView;
import softuni.bg.iLearn.service.AdminService;
import softuni.bg.iLearn.service.UserService;

@Controller
@Slf4j
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

    @PostMapping("/delete/{username}")
    public String postDelete(@PathVariable String username) {

        userService.deleteUserByUsername(username);

       return "redirect:/all-users";
    }
    @PostMapping("/update-role/{username}")
    public String postUpdate(@PathVariable String username) {

        userService.updateUserRoleByUsername(username);

        return "redirect:/all-users";
    }

    @PostMapping("/ban/{username}")
    public String postBan(@PathVariable String username) {

        userService.banUserByUsername(username);

        return "redirect:/all-users";
    }

}
