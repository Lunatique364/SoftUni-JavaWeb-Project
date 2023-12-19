package softuni.bg.iLearn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.bg.iLearn.dto.DeleteUserDTO;
import softuni.bg.iLearn.model.view.AllUsersView;
import softuni.bg.iLearn.service.AdminService;
import softuni.bg.iLearn.service.UserService;

import java.time.LocalDate;

import static softuni.bg.iLearn.utils.CommonMessages.ADMIN_BAN;

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
    @ModelAttribute("deleteUserDTO")
    public DeleteUserDTO initUser() {
        return new DeleteUserDTO();
    }
    @GetMapping("/all-users")
    public String allUsersPage() {
        return "all-users";
    }

    @PostMapping("/delete/{username}")
    public String postDelete(@PathVariable String username) {

        if (!username.equals("admin")) {
            userService.deleteUserByUsername(username);
        }
       return "redirect:/all-users";
    }

    @PostMapping("/ban/{username}")
    public String postBan(@PathVariable String username) {

        if (!username.equals("admin")) {
            userService.banUserByUsername(username);
            log.info(String.format(ADMIN_BAN, username, LocalDate.now()));
        }

        return "redirect:/all-users";
    }

}
