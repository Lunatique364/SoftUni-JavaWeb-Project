package softuni.bg.iLearn.service;

import softuni.bg.iLearn.dto.NewsletterSubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    boolean addSubscription(NewsletterSubscriptionDTO subscriptionDTO);

    List<String> getEmails();
}
