package softuni.bg.iLearn.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import softuni.bg.iLearn.model.MailDetails;
import softuni.bg.iLearn.service.MailService;


@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(MailDetails mailDetails) {

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
}
