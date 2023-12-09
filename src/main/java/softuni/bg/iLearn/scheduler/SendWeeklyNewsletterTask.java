package softuni.bg.iLearn.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.bg.iLearn.model.WeeklyNewsletter;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.service.SubscriptionService;
import softuni.bg.iLearn.service.WeeklyNewsletterService;

import java.util.List;


@Component
public class SendWeeklyNewsletterTask {

    private final WeeklyNewsletterService newsletterService;

    private final SubscriptionService subscriptionService;
    private final MailService mailService;

    @Autowired
    public SendWeeklyNewsletterTask(WeeklyNewsletterService newsletterService, SubscriptionService subscriptionService, MailService mailService) {
        this.newsletterService = newsletterService;
        this.subscriptionService = subscriptionService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 0 9 * * MON")
    private void sendWeeklyNewsletter() {

        List<String> emails = subscriptionService.getEmails();
        WeeklyNewsletter newsletter = newsletterService.getWeeklyNewsletter();
        emails.forEach(e -> mailService.sendWeeklyNewsletter(newsletter, e));

    }
}
