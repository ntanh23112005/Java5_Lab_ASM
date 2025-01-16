package poly.java5.test;

import java.util.Date;

import lombok.Builder.Default;

public class Staff {
String id;
String name;
Boolean gender = true;
Date birthday = new Date();
String photo = "photo.jpg";
Double salary;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Double getSalary() {
	return salary;
}
public void setSalary(Double salary) {
	this.salary = salary;
}
public Staff(String id, String name, Boolean gender, Date birthday, String photo, Double salary) {
	super();
	this.id = id;
	this.name = name;
	this.gender = gender;
	this.birthday = birthday;
	this.photo = photo;
	this.salary = salary;
}
public Staff() {
	super();
}



}
