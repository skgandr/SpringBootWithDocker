package user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	/** The Constant counter. */
	private static final AtomicLong counter = new AtomicLong();

	/** The users. */
	private static List<User> users;

	static {
		users = populateDummyUsers();
	}
	
	/* (non-Javadoc)
	 * @see user.service.UserService#findById(long)
	 */
	@Override
	public User findById(long id) {
		return users.stream().filter(u -> u.getId() == id).findAny().orElse(null);
	}

	/* (non-Javadoc)
	 * @see user.service.UserService#findByName(java.lang.String)
	 */
	@Override
	public User findByName(String name) {
		return users.stream().filter(u -> u.getName().equals(name)).findAny().orElse(null);
	}

	/* (non-Javadoc)
	 * @see user.service.UserService#saveUser(user.model.User)
	 */
	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);

	}

	/* (non-Javadoc)
	 * @see user.service.UserService#updateUser(user.model.User)
	 */
	@Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	/* (non-Javadoc)
	 * @see user.service.UserService#deleteUserById(long)
	 */
	@Override
	public void deleteUserById(long id) {
		users.removeIf(p -> p.getId() == id);

	}

	/* (non-Javadoc)
	 * @see user.service.UserService#findAllUsers()
	 */
	@Override
	public List<User> findAllUsers() {
		return users;
	}

	/* (non-Javadoc)
	 * @see user.service.UserService#deleteAllUsers()
	 */
	@Override
	public void deleteAllUsers() {
		users.clear();

	}

	/* (non-Javadoc)
	 * @see user.service.UserService#isUserExist(user.model.User)
	 */
	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	/**
	 * Populate dummy users.
	 *
	 * @return the list
	 */
	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Sam", 25, 1000));
		users.add(new User(counter.incrementAndGet(), "Tom", 30, 5000));
		users.add(new User(counter.incrementAndGet(), "Jerome", 25, 1000));
		users.add(new User(counter.incrementAndGet(), "Silvia", 35, 10000));
		return users;
	}
	
}
