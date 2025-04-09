package poly.java5.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data

@Entity
@Table(name = "J5_Students")
public class Student {

	@Id
	@Column(name = "id")
	String id;
	
	@Column(name = "fullname")
	String fullname;
	
	@Column(name = "gender")
	Boolean gender;
	
	@Column(name = "mark")
	Double mark;
	
	@Column(name = "photo")
	String photo;
}
