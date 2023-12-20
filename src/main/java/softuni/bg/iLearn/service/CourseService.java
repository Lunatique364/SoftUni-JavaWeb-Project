package softuni.bg.iLearn.service;

import org.springframework.data.jpa.repository.Query;
import softuni.bg.iLearn.model.Course;
import softuni.bg.iLearn.model.view.CoursesView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseService{

    Optional<List<Course>> findAllByDateAddedLessThan(LocalDateTime localDateTime);
    Optional<List<Course>> findAllByDateAddedBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

    Optional<List<Course>> findAllCourses();
    List<CoursesView> findAllCoursesView();
}
