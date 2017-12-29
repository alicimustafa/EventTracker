package data;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.User;

@Transactional
@Repository
public class AuotDAOImpl implements AuotDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User login(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(json, User.class);
			String query = "SELECT u from User u WHERE u.userName = :name";
			List<User> userList = em.createQuery(query, User.class)
					.setParameter("name", user.getUserName())
					.getResultList();
			if(userList.size() > 0) {
				if(encoder.matches(user.getPassword(), userList.get(0).getPassword())) {
					return userList.get(0);
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User register(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(json, User.class);
			System.out.println("before " + user.getPassword());
			String encryptedPW = encoder.encode(user.getPassword());
			user.setPassword(encryptedPW);
			em.persist(user);
			em.flush();
			System.out.println("after " + user.getPassword());
			return user;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
