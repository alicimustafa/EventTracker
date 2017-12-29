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
import entity.Activity;
import entity.Destination;
import entity.User;

@RestController
public class TravelControllers {

	@Autowired
	TravelDAO dao;
	
	@RequestMapping(path="ping", method= RequestMethod.GET)
	public String ping() {
		return "pong";
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
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(path="users/{id}", method = RequestMethod.PUT)
	public User userUpdat(@PathVariable int id, 
			@RequestBody String json) {
		return dao.updateUser(json, id);
	}
	
	@RequestMapping(path="users/{id}/destinations", 
			method = RequestMethod.GET)
	public List<Destination> indexDestination(@PathVariable int id){
		return dao.destinatonListForUsers(id);
	}
	
	@RequestMapping(path = "users/{id}/destinations", 
			method = RequestMethod.POST)
	public List<Destination> destinationCreate(@PathVariable int id, 
			@RequestBody String json, HttpServletResponse res) {
		boolean destationAdded = dao.addDestination(json, id);
		if(destationAdded) {
			return dao.destinatonListForUsers(id);
		}
		res.setStatus(400);
		return null;
	}
	
	@RequestMapping(path = "users/{userId}/destinations/{id}", 
			method = RequestMethod.DELETE)
	public List<Destination> destinationDestroy(@PathVariable int id,
			HttpServletResponse res) {
		int userId = dao.removeDestination(id);
		if(userId > 0){
			return dao.destinatonListForUsers(userId); 
		}
		res.setStatus(400);
		return null;
	}
	
	@RequestMapping(path = "users/{userId}/destinations/{id}", 
			method = RequestMethod.GET)
	public Destination showDestination(@PathVariable int id) {
		return dao.getDestinationForUser(id);
	}
	
	@RequestMapping(path = "users/{userId}/destinations/{id}",
			method = RequestMethod.PUT)
	public List<Destination> updateDestination(@PathVariable int id,
			@RequestBody String json) {
		int userId = dao.updateDestination(json, id).getUser().getId();
		return dao.destinatonListForUsers(userId);
	}
	
	@RequestMapping(path = "users/{userId}/destinations/{id}/activities",
			method = RequestMethod.GET)
	public List<Activity> indexActivity(@PathVariable int id){
		return dao.activitiesForDestination(id);
	}
	
	@RequestMapping(
			path = "users/{user}/destinations/{dest}/activities/{id}",
			method = RequestMethod.GET)
	public Activity showActivity(@PathVariable int id) {
		return dao.getActivityById(id);
	}
	
	@RequestMapping(
			path = "users/{user}/destinations/{id}/activities",
			method = RequestMethod.POST)
	public List<Activity> createActivity(@RequestBody String json,
			@PathVariable int id, HttpServletResponse res){
		if(dao.addActivity(json, id)) {
			return dao.activitiesForDestination(id);
		}
		res.setStatus(400);
		return null;
	}
	
	@RequestMapping(
			path = "users/{user}/destinations/{dest}/activities/{id}",
			method = RequestMethod.DELETE)
	public List<Activity> destroyActivity(@PathVariable int id,
			HttpServletResponse res){
		int destId = dao.removeActivity(id);
		if(destId > 0) {
			return dao.activitiesForDestination(destId);
		}
		res.setStatus(400);
		return null;
	}
	
	@RequestMapping(
			path = "users/{user}/destinations/{dest}/activities/{id}",
			method = RequestMethod.PUT)
	public Activity updateActivity(@PathVariable int id,
			@RequestBody String json,
			HttpServletResponse res) {
		Activity act = dao.upadateActivity(json, id);
		if(act != null) {
			return act;
		}
		res.setStatus(400);
		return null;
	}
}
