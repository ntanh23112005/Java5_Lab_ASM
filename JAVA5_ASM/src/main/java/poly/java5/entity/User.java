package poly.java5.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

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
	private String photo = "";
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	@Column(name = "trangthai")
	private boolean trangThai;
	
	@Column(name="activationtoken")
	private String activationtoken;
}
