package softuni.bg.iLearn.service;


import softuni.bg.iLearn.dto.ContactDTO;
import softuni.bg.iLearn.model.MailDetails;

public interface MailService {
    void sendRegistrationMail(MailDetails mailDetails);

    void receiveContact(ContactDTO contactDTO);
}
