package data;

import java.util.List;

import entity.Destination;
import entity.User;

public interface TravelDAO {
	
	public List<User> getUserList();
	public User addUser(String json);
	public int checkUserLog(String json);
	public boolean deletUser(int id);
	
	public User getUserInfo(int id);
	public boolean addDestination(String json, int id);
	public User removeDestination(int id);
	public User updateDestination(String id);
	
	public Destination getDestinationForUser(int id);
	public Destination addActivity(String json, int id);
	public Destination removeActivity(int id);
	
	
}
