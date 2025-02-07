package poly.java5.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
	@NotBlank(message = "Chưa nhập email")
	@Email(message = "Email không đúng định dạng")
	String id;
	
	@NotBlank(message = "Chưa nhập họ và tên")
	String fullname;
	
	@Default
	String photo = "photo.jpg";
	
	@NotNull(message = "Chưa chọn giới tính")
	@Default
	Boolean gender;
	
	@NotNull(message = "Chưa nhập ngày sinh")
	@Past(message = "Ngày sinh tào lao")
	@Default
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	Date birthday = new Date();
	
	@Min(value = 1000, message = "Lương tối thiểu 1000")
	@NotNull(message = "Chưa nhập lương")
	@Default
	private double salary = 12345.6789;
	@Default
	private Integer level = 0;
	
	
	
	public Staff(String id, String fullname, String photo, Boolean gender, Date birthday, double salary,
			Integer level) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.salary = salary;
		this.level = level;
	}
	public Staff() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	
	
}
