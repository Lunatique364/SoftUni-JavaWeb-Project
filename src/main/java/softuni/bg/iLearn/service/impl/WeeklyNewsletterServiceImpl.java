package softuni.bg.iLearn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.repository.WeeklyNewsletterRepository;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.service.WeeklyNewsletterService;

@Service
public class WeeklyNewsletterServiceImpl implements WeeklyNewsletterService {

    private final WeeklyNewsletterRepository newsletterRepository;
    private final MailService mailService;

    @Autowired
    public WeeklyNewsletterServiceImpl(WeeklyNewsletterRepository newsletterRepository, MailService mailService) {
        this.newsletterRepository = newsletterRepository;
        this.mailService = mailService;
    }
}
