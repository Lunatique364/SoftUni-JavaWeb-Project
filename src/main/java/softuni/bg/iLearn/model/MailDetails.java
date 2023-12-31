package softuni.bg.iLearn.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDetails {
    private String sender;
    private String recipient;
    private String subject;
    private String message;

}
