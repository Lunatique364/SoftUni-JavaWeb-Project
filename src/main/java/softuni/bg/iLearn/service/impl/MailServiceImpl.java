package softuni.bg.iLearn.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.dto.ContactDTO;
import softuni.bg.iLearn.model.MailDetails;
import softuni.bg.iLearn.service.MailService;
import softuni.bg.iLearn.utils.CommonMessages;


@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRegistrationMail(MailDetails mailDetails) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);

        try {
            mimeMessageHelper.setFrom(mailDetails.getSender());
            mimeMessageHelper.setTo(mailDetails.getRecipient());
            mimeMessageHelper.setSubject(mailDetails.getSubject());
            mimeMessageHelper.setText(mailDetails.getMessage());

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void receiveContact(ContactDTO contactDTO) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setFrom(CommonMessages.EMAIL_SENDER);
            mimeMessageHelper.setReplyTo(CommonMessages.EMAIL_SENDER);
            mimeMessageHelper.setTo(CommonMessages.EMAIL_SENDER);
            mimeMessageHelper.setSubject(contactDTO.getSubject());
            mimeMessageHelper.setText(String.format(CommonMessages.EMAIL_CONTACT_BODY, contactDTO.getSender(), contactDTO.getEmail(), contactDTO.getMessage()));

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
