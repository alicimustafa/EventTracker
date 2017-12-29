package controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.AuotDAO;
import entity.User;

@RestController
@RequestMapping("/auth")
public class AoutController {

	@Autowired
	AuotDAO dao;
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public User register(@RequestBody String json,
			HttpSession session,
			HttpServletResponse res) {
		User user = dao.register(json);
		System.out.println(json);
//		if(user != null) {
//			return user;
//		}
		res.setStatus(200);
		return user;
	}
	
	@RequestMapping(path ="/login", method = RequestMethod.POST)
	public User login(@RequestBody String json, 
			HttpSession session,
			HttpServletResponse res) {
		User user = dao.login(json);
		if(user != null) {
			session.setAttribute("user", user);
			return user;
		}
		res.setStatus(401);
		return null;
	}
	
	@RequestMapping(path="/logout", method = RequestMethod.POST)
	public Boolean logout(HttpSession session, 
			HttpServletResponse res) {
		session.removeAttribute("user");
		if(session.getAttribute("user") == null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(path = "/unauthorized")
	  public String unauth(HttpServletResponse response) {
	    response.setStatus(401);
	    return "unauthorized";
	  }
}
