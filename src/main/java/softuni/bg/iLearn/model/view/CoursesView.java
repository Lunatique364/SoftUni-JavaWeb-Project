package softuni.bg.iLearn.model.view;

import lombok.*;
import softuni.bg.iLearn.model.Author;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursesView {

    private String courseName;
    private String category;
    private String date;
    private String author;
    private String price;
}
