package softuni.bg.iLearn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.bg.iLearn.model.Course;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<List<Course>> findAllByDateAddedLessThan(LocalDateTime localDateTime);
    Optional<List<Course>> findAllByDateAddedBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

    @Query(value = "SELECT * from Courses", nativeQuery = true)
    Optional<List<Course>> findAllCourses();
}
