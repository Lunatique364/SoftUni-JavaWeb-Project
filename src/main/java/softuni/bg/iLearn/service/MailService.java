package softuni.bg.iLearn.service;


import softuni.bg.iLearn.model.MailDetails;

public interface MailService {
    void sendMail(MailDetails mailDetails);
}
