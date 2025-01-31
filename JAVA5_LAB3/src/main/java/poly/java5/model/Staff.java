package poly.java5.model;

import java.util.Date;

public class Staff {
String id;
String fullname;
String photo = "photo.jpg";
Boolean gender = true;
Date birthday = new Date();
Double salary = 12345.6789;
Integer level = 0;
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
public Double getSalary() {
	return salary;
}
public void setSalary(Double salary) {
	this.salary = salary;
}
public Integer getLevel() {
	return level;
}
public void setLevel(Integer level) {
	this.level = level;
}
public Staff(String id, String fullname, String photo, Boolean gender, Date birthday, Double salary, Integer level) {
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


}