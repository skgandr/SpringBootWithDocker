package user.service;

import java.util.List;

import user.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {
    
    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     */
    User findById(long id);
     
    /**
     * Find by name.
     *
     * @param name the name
     * @return the user
     */
    User findByName(String name);
     
    /**
     * Save user.
     *
     * @param user the user
     */
    void saveUser(User user);
     
    /**
     * Update user.
     *
     * @param user the user
     */
    void updateUser(User user);
     
    /**
     * Delete user by id.
     *
     * @param id the id
     */
    void deleteUserById(long id);
 
    /**
     * Find all users.
     *
     * @return the list
     */
    List<User> findAllUsers(); 
     
    /**
     * Delete all users.
     */
    void deleteAllUsers();
     
    /**
     * Checks if is user exist.
     *
     * @param user the user
     * @return true, if is user exist
     */
    public boolean isUserExist(User user);
     
}

