package poly.java5.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service("cookieService")
public class CookieService {
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpServletRequest request;
	
	//tạo cookie và gửi về cilent
	public Cookie create(String name, String value, int expiry) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath("/");
		
		response.addCookie(cookie); //gửi về cilent
		
		System.out.println("CookieService.create()");
		return null;
	}

	//đọc cookie theo tên được gửi trong request
	public Cookie get(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					System.out.println("CookieService.get() thành công");
					return cookie;
				}
			}
		}
		
		System.out.println("CookieService.get() thất bại");
		return null;
	}
	
	public void delete(String name) {
		this.create(name, "", 0);
		System.out.println("CookieService.delete()");
	}
	
	public String getValue(String name) {
		Cookie cookie = this.get(name);
		System.out.println("CookieService.getValue()");
		return cookie != null ? cookie.getValue(): "";
	}
}
