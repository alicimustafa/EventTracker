package data;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Destination;
import entity.User;

@Transactional
@Repository
public class TravelDAOImpl implements TravelDAO {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<User> getUserList() {
		String sql = "SELECT u FROM User u";
		return em.createQuery(sql, User.class).getResultList();
	}

	@Override
	public User addUser(String json) {
		ObjectMapper mapper = new ObjectMapper();
		User user;
		try {
			user = mapper.readValue(json, User.class);
			em.persist(user);
			em.flush();
			return user;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deletUser(int id) {
		User user = em.find(User.class, id);
		if(user != null) {
			em.remove(user);
			return true;
		}
		return false;
	}

	@Override
	public int checkUserLog(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(json, User.class);
			String sql = "SELECT u FROM User u WHERE u.userName = :name";
			List<User> userList = em.createQuery(sql, User.class)
					.setParameter("name", user.getUserName())
					.getResultList();
			System.out.println(userList);
			if(userList.size() > 0) {
				System.out.println("user name: " + userList.get(0).getUserName());
				System.out.println("password: " + userList.get(0).getPassword());
				if(userList.get(0).getPassword().equals(user.getPassword())) {
					return userList.get(0).getId();
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User getUserInfo(int id) {		
		return em.find(User.class, id);
	}

	@Override
	public User addDestination(String json, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User removeDestination(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateDestination(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination getDestinationForUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination addActivity(String json, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination removeActivity(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
