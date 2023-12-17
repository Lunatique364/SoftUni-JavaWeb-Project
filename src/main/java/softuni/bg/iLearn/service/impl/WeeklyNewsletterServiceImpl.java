package softuni.bg.iLearn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.model.WeeklyNewsletter;
import softuni.bg.iLearn.repository.WeeklyNewsletterRepository;
import softuni.bg.iLearn.service.CourseService;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.service.SubscriptionService;
import softuni.bg.iLearn.service.WeeklyNewsletterService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class WeeklyNewsletterServiceImpl implements WeeklyNewsletterService {

    private final WeeklyNewsletterRepository newsletterRepository;
    private final CourseService courseService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public WeeklyNewsletterServiceImpl(WeeklyNewsletterRepository newsletterRepository, CourseService courseService, SubscriptionService subscriptionService, MailService mailService) {
        this.newsletterRepository = newsletterRepository;
        this.courseService = courseService;
        this.subscriptionService = subscriptionService;
    }


    @Override
    public WeeklyNewsletter getWeeklyNewsletter() {

        WeeklyNewsletter newsletter = new WeeklyNewsletter();

        newsletter.setCourses(courseService
                .findAllByDateAddedBetween(LocalDateTime.now(), LocalDateTime.now().minusDays(7))
                .orElse(new ArrayList<>()));

        newsletter.setDate(LocalDateTime.now());
        newsletter.setEmails(subscriptionService.getEmails());
        newsletterRepository.save(newsletter);

        return newsletter;

    }



}
