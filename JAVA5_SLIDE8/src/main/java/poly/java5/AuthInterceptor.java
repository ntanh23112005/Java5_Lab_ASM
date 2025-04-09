package poly.java5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.java5.entity.Const;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(session.getAttribute("user") != null) {
			return true;
		}
		session.setAttribute(Const.SECURED_URI, request.getRequestURI());
		response.sendRedirect("/account/login");
		return false;
	}
}
