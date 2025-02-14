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
//	@Default
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

}
