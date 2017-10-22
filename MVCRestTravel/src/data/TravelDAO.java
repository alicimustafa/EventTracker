package data;

import java.util.List;

import entity.User;

public interface TravelDAO {
	
	public List<User> getUserList();
	public User addUser(String json);
	public boolean deletUser(int id);
	public User checkUserLog(String json);
	
}
