package poly.java5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.java5.AuthInterceptor;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	AuthInterceptor authInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/account/login","/account/register","/trang-chu");
	}
}
