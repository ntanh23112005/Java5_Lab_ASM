package poly.java5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.java5.controller.LayoutInterceptor;
import poly.java5.controller.LogInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	LogInterceptor logInterceptor; 
	@Autowired
	AuthInterceptor authInterceptor;
	@Autowired
	LayoutInterceptor layoutInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor)
		.addPathPatterns("/poly/**")
		.excludePathPatterns("/poly/url2");
		
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/account/**")
		.excludePathPatterns("/account/login");
		
		registry.addInterceptor(layoutInterceptor)
		.addPathPatterns("/**");
	}
}
