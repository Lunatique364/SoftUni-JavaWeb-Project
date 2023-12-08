package softuni.bg.iLearn.controller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.bg.iLearn.dto.ContactDTO;
import softuni.bg.iLearn.dto.EditProfileDTO;
import softuni.bg.iLearn.service.MailService;

@Controller
public class ContactController {

    private final MailService mailService;

    public ContactController(MailService mailService) {
        this.mailService = mailService;
    }

    @ModelAttribute("contactDTO")
    public ContactDTO initContact() {
        return new ContactDTO();
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(ContactDTO contactDTO) {
        mailService.receiveContact(contactDTO);
        return "/contact";
    }
}
