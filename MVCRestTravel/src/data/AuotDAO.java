package data;

import entity.User;

public interface AuotDAO {

	public User login(String json);
	public User register(String json);
}
