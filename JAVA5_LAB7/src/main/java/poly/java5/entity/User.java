package poly.java5.entity;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Users")
public class User {
	@Id
	private String username;
	private String password;
	private boolean enabled;
	private String fullname;
	private String mobile;
	@Default
	private String photo = "user.png";
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> authorities;
}
