package poly.java5.entity;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Roles")
public class Role {
	@Id
	private String id;
	private String name;
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<Authority> authorities;
}
