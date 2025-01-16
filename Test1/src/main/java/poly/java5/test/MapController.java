package poly.java5.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/poly/")
public class MapController {

	@RequestMapping("url1") //no value (default: value)
	public String method1() {
		return "/poly/page.html";
	}
	
//	@RequestMapping(value="url2", method = RequestMethod.GET)
	@GetMapping(value = "url2") //get
	public String method2() {
		return "/poly/page.html";
	}
	
	@RequestMapping(value="url3", method = {RequestMethod.GET, RequestMethod.POST})
	public String method3() {
		return "/poly/page.html";
	}
	
	//Mapping urls with 1 method
	@RequestMapping({"url5, url6"})
	public String method56() {
		return "/poly/page.html";
	}
}
