package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.TravelDAO;
import entity.User;

@RestController
public class TravelControllers {

	@Autowired
	TravelDAO dao;
	
	@RequestMapping(path="ping", method= RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path="login", method = RequestMethod.POST)
	public String logUser(@RequestBody String json , HttpServletResponse res) {
		Integer userId = dao.checkUserLog(json);
		if(userId == 0) {
			res.setStatus(401);
		}
		return userId.toString();
	}
	
	@RequestMapping(path="users", method= RequestMethod.GET)
	public List<User> usersIndex() {
		return dao.getUserList();
	}
	
	@RequestMapping(path="users/{id}", method= RequestMethod.GET)
	public User showUser(@PathVariable int id) {
		return dao.getUserInfo(id);
	}
	
	@RequestMapping(path="users", method= RequestMethod.POST)
	public User usersCreate(@RequestBody String json) {		
		return dao.addUser(json);
	}
	
	@RequestMapping(path="users/{id}", method = RequestMethod.DELETE)
	public String userDestroy(@PathVariable int id) {
		if(dao.deletUser(id)) {
			return "succes";
		}
		return "fail";
	}
	
	
}
