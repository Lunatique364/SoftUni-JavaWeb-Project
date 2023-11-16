package softuni.bg.iLearn.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
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

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            mimeMessage.setSubject(mailDetails.getSubject());
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailDetails.getSender());
            helper.setTo(mailDetails.getRecipient());
            helper.setText(mailDetails.getMessage(), true);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
