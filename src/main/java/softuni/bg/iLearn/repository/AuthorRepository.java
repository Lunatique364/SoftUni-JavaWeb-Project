package softuni.bg.iLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.bg.iLearn.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
