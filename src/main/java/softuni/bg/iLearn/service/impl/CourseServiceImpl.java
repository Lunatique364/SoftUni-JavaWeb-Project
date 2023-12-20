package softuni.bg.iLearn.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.model.Course;
import softuni.bg.iLearn.model.view.CoursesView;
import softuni.bg.iLearn.repository.CourseRepository;
import softuni.bg.iLearn.service.CourseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<Course>> findAllByDateAddedLessThan(LocalDateTime localDateTime) {
        return courseRepository.findAllByDateAddedLessThan(localDateTime);
    }

    @Override
    public Optional<List<Course>> findAllByDateAddedBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return courseRepository.findAllByDateAddedBetween(localDateTime1, localDateTime2);
    }

    @Override
    public Optional<List<Course>> findAllCourses() {
        return Optional.empty();
    }

    @Override
    public List<CoursesView> findAllCoursesView() {
        Optional<List<Course>> allCourses = courseRepository.findAllCourses();

        return allCourses.map(courses -> courses.stream().map(this::toCourseView).collect(Collectors.toList())).orElse(null);

    }

    private CoursesView toCourseView(Course c) {
        return CoursesView
                .builder()
                .courseName(c.getName())
                .author(c.getAuthor().getFirstName())
                .date(c.getDateAdded().toString())
                .price(c.getPrice().toString())
                .category(c.getCategory().name())
                .build();
    }
}
