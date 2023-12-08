package softuni.bg.iLearn.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.bg.iLearn.service.WeeklyNewsletterService;


@Component
public class SendWeeklyNewsletterTask {

    private final WeeklyNewsletterService newsletterService;

    @Autowired
    public SendWeeklyNewsletterTask(WeeklyNewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @Scheduled(cron = "0 0 9 * * MON")
    private void sendWeeklyNewsletter() {

    }
}
