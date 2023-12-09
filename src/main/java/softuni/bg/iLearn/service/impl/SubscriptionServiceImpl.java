package softuni.bg.iLearn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.dto.NewsletterSubscriptionDTO;
import softuni.bg.iLearn.model.Subscription;
import softuni.bg.iLearn.repository.SubscriptionRepository;
import softuni.bg.iLearn.service.SubscriptionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addSubscription(NewsletterSubscriptionDTO subscriptionDTO) {
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);

        if (subscriptionRepository.findByEmail(subscription.getEmail()).isPresent()) {
            return false;
        }

        subscriptionRepository.save(subscription);
        return true;
    }

    @Override
    public List<String> getEmails() {
        return subscriptionRepository.findAllSubscriptions().stream().map(Subscription::getEmail).collect(Collectors.toList());
    }
}
