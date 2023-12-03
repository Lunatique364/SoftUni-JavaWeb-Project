//package softuni.bg.iLearn.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import softuni.bg.iLearn.model.MailDetails;
//import softuni.bg.iLearn.service.MailService;
//
//@RestController
//public class EmailController {
//
//    private final MailService mailService;
//
//    public EmailController(MailService mailService) {
//        this.mailService = mailService;
//    }
//
//    @PostMapping
//    public ResponseEntity sendEmail(@RequestBody MailDetails mailDetails) {
//        this.mailService.sendMail(mailDetails);
//        return ResponseEntity.ok("Success");
//    }
//}
