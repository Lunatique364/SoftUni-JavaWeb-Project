package softuni.bg.iLearn.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.bg.iLearn.model.view.CoursesView;
import softuni.bg.iLearn.service.CourseService;

import java.util.List;

@RestController
public class CoursesRestController {

    private final CourseService courseService;

    @Autowired
    public CoursesRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/api/courses")
    public ResponseEntity<List<CoursesView>> initCourses() {
        List<CoursesView> allCoursesView = courseService.findAllCoursesView();
        return ResponseEntity.ok(allCoursesView);
    }
}
