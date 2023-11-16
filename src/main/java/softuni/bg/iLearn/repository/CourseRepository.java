package softuni.bg.iLearn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.bg.iLearn.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
