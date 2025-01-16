package poly.java5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.model.Staff;

@Controller
public class StaffInformationController {
@RequestMapping("/staff/infor")
public String showInfor(Model model) {
	Staff st = new Staff();
	st.setId("NV001");
	st.setFullname("nGuyễn thẾ anH");
	model.addAttribute("staff", st);
	return "lab3/infor";
}

@RequestMapping("/staff/list")
public String showListStaff(Model model) {
	List<Staff> list = new ArrayList<>();
	Staff st = new Staff();
	st.setId("NV001");
	st.setFullname("nGuyễn thẾ anH");
	st.setLevel(0);
	list.add(st);
	
	Staff st2 = new Staff();
	st2.setId("NV002");
	st2.setFullname("Nguyễn Thế Em");
	st.setLevel(1);
	list.add(st2);
	
	Staff st3 = new Staff();
	st3.setId("NV003");
	st3.setFullname("Nguyễn Thế");
	st.setLevel(2);
	list.add(st3);
	
	Staff st4 = new Staff();
	st4.setId("NV004");
	st4.setFullname("Nguyễn Thế Em");
	st.setLevel(3);
	list.add(st4);
	
	model.addAttribute("list", list);
	
	//Bài 3
//	return "lab3/list";
	
	//Bài 4
//	return "lab3/list-status";
	
	//Bài 5
	return "lab3/list-controls";
}
}
