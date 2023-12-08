package softuni.bg.iLearn.model.view;

import lombok.*;
import softuni.bg.iLearn.model.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllUsersView {
    List<User> allUsers;

}
