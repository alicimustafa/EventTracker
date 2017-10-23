package data;

import java.util.List;

import entity.Destination;
import entity.User;

public interface TravelDAO {
	
	public int checkUserLog(String json);
	
	public List<User> getUserList();
	public User getUserInfo(int id);
	public User addUser(String json);
	public boolean deletUser(int id);
	public User updateUser(String json, int id);
	
	public List<Destination> destinatonListForUsers(int id);
	public int removeDestination(int id);
	public Destination getDestinationForUser(int id);
	public boolean addDestination(String json, int id);
	public Destination updateDestination(String json, int id);
	
	public Destination addActivity(String json, int id);
	public Destination removeActivity(int id);
	
	
}
