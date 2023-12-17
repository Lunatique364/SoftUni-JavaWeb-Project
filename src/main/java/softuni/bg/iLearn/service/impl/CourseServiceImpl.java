package softuni.bg.iLearn.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.model.Course;
import softuni.bg.iLearn.repository.CourseRepository;
import softuni.bg.iLearn.service.CourseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<List<Course>> findAllByDateAddedLessThan(LocalDateTime localDateTime) {
        return courseRepository.findAllByDateAddedLessThan(localDateTime);
    }

    @Override
    public Optional<List<Course>> findAllByDateAddedBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return courseRepository.findAllByDateAddedBetween(localDateTime1, localDateTime2);
    }
}
