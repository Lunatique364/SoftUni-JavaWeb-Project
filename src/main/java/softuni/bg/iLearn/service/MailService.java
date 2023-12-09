package softuni.bg.iLearn.service;


import softuni.bg.iLearn.dto.ContactDTO;
import softuni.bg.iLearn.dto.NewsletterSubscriptionDTO;
import softuni.bg.iLearn.model.MailDetails;
import softuni.bg.iLearn.model.WeeklyNewsletter;

public interface MailService {
    void sendRegistrationMail(MailDetails mailDetails);

    void receiveContact(ContactDTO contactDTO);

    void sendWeeklyNewsletter(WeeklyNewsletter weeklyNewsletter, String email);

    void sendResetPasswordMail(MailDetails mailDetails);
}
