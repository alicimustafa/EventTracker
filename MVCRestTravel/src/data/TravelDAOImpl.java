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

import entity.Activity;
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
	public User getUserInfo(int id) {		
		return em.find(User.class, id);
	}
	
	@Override
	public User updateUser(String json, int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User inputUser = mapper.readValue(json, User.class);
			User user = em.find(User.class, id);
			user.setUserName(inputUser.getUserName());
			user.setPassword(inputUser.getPassword());
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
	public List<Destination> destinatonListForUsers(int id){
		String sql = "SELECT d FROM Destination d WHERE d.user.id = :id";
		return em.createQuery(sql, Destination.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	@Override
	public boolean addDestination(String json, int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Destination dest = mapper.readValue(json, Destination.class);
			User user = em.find(User.class, id);
			if(user != null) {
				dest.setUser(user);
				em.persist(dest);
				return true;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int removeDestination(int id) {
		Destination dest = em.find(Destination.class, id);
		if(dest != null) {
			int userId = dest.getUser().getId();
			em.remove(dest);
			return userId;
		}
		return 0;
	}

	@Override
	public Destination updateDestination(String json, int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Destination destInfo = mapper.readValue(json, Destination.class);
			Destination dest = em.find(Destination.class, id);
			dest.setName(destInfo.getName());
			dest.setImgUrl(destInfo.getImgUrl());
			return dest;
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
	public Destination getDestinationForUser(int id) {
		return em.find(Destination.class, id);
	}

	@Override
	public List<Activity> activitiesForDestination(int id) {
		String sql = "SELECT a FROM Activity a WHERE a.destination.id = :id";
		return em.createQuery(sql, Activity.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public Activity getActivityById(int id) {
		return em.find(Activity.class, id);
	}

	@Override
	public int removeActivity(int id) {
		Activity act = em.find(Activity.class, id);
		if(act != null) {
			int destId = act.getDestination().getId();
			em.remove(act);
			return destId;
		}
		return 0;
	}

	@Override
	public boolean addActivity(String json, int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Activity act = mapper.readValue(json, Activity.class);
			Destination dest = em.find(Destination.class, id);
			act.setDestination(dest);
			em.persist(act);
			return true;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Activity upadateActivity(String json, int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Activity actInfo = mapper.readValue(json, Activity.class);
			Activity act = em.find(Activity.class, id);
			if(act != null) {
				act.setName(actInfo.getName());
				act.setDate(actInfo.getDate());
				act.setCost(actInfo.getCost());
				return act;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
