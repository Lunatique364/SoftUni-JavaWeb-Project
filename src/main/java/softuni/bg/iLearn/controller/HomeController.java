package softuni.bg.iLearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.bg.iLearn.dto.ContactDTO;

@Controller
public class HomeController {


    public HomeController() {
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutUsPage() {
        return "about-us";
    }


}
