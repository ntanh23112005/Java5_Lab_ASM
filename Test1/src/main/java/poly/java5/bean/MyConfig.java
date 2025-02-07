package poly.java5.bean;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import poly.java5.service.CookieService;

@Configuration
public class MyConfig {
	@Bean("myBean")
	public List<String> getList() {
		return List.of("Thiên", "An", "Bé Sol");
	}
	
//	@Bean
//	public CookieService getCookieService() {
//		return new CookieService();
//	}
}
