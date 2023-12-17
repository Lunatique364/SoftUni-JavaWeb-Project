package softuni.bg.iLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.bg.iLearn.model.WeeklyNewsletter;

import java.util.Optional;


public interface WeeklyNewsletterRepository extends JpaRepository<WeeklyNewsletter, Long> {

}
