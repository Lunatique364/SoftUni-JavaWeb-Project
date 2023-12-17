package softuni.bg.iLearn.service;

import softuni.bg.iLearn.model.Course;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseService{

    Optional<List<Course>> findAllByDateAddedLessThan(LocalDateTime localDateTime);
    Optional<List<Course>> findAllByDateAddedBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);
}
