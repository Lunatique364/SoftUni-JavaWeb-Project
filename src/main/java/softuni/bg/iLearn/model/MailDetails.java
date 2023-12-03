package softuni.bg.iLearn.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDetails {
    private String sender;
    private String recipient;
    private String subject;
    private String message;

    public MailDetails() {

    }

    public MailDetails(String sender, String recipient, String subject, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

}
