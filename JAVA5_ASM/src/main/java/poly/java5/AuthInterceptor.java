package poly.java5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.java5.dao.AuthorityDAO;
import poly.java5.entity.User;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	HttpSession session;
	
	@Autowired
	AuthorityDAO authorityDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    User user = (User) session.getAttribute("loggedUser");
	    

	    if (user == null || !authorityDAO.existsByUserUsernameAndRoleId(user.getUsername(), "R001")) {
	        response.sendRedirect(user == null ? "/account/login" : "/403");
	        return false;
	    }
	    return true;
	}
}
