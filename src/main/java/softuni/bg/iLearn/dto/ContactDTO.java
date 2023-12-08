package softuni.bg.iLearn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {
    private String sender;
    private String email;
    private String subject;
    private String message;
}
