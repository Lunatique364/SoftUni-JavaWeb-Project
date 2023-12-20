package softuni.bg.iLearn.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softuni.bg.iLearn.model.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    private Author author;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDateTime dateAdded;

    @Column
    private BigDecimal price;

}
