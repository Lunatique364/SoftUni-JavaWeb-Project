package softuni.bg.iLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.bg.iLearn.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = "SELECT * from SUBSCRIPTION", nativeQuery = true)
    List<Subscription> findAllSubscriptions();

    Optional<Subscription> findByEmail(String email);

}
