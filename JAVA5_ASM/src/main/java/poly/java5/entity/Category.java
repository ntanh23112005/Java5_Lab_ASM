package poly.java5.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categories")
@AllArgsConstructor @NoArgsConstructor @Data @Getter
@Setter
public class Category {

    @Id
    @Column(name = "id")
    private String id; 

    private String name;

}
