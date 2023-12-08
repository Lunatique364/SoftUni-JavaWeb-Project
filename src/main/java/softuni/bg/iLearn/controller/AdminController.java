package softuni.bg.iLearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import softuni.bg.iLearn.model.view.AllUsersView;
import softuni.bg.iLearn.service.AdminService;
import softuni.bg.iLearn.service.UserService;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute("allUsersView")
    public AllUsersView initUsers() {
        return new AllUsersView(adminService.getAllUsers());
    }
    @GetMapping("/all-users")
    public String allUsersPage() {
        return "all-users";
    }


}
