package softuni.bg.iLearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {

    @GetMapping("/courses")
    public String coursesPage() {
        return "courses";
    }




}
