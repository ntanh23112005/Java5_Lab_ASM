package poly.java5.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Account")
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String role;
}