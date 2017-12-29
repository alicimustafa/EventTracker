package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import entity.User;

public class DataSecurityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	    if (request.getSession().getAttribute("user") != null) {
	    		System.out.println(request.getRequestURI());
	    		int userId = Integer.parseInt(request.getRequestURI().split("/")[4]);
	    		if(userId == ((User)request.getSession().getAttribute("user")).getId()) {
	    			return true;
	    		}
	    }
	    response.sendRedirect("/MVCRestTravel/rest/auth/unauthorized");
	    return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
