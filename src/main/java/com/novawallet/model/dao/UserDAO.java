package com.novawallet.model.dao;

import com.novawallet.model.entity.User;
import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDAO {
    /**
     * Add user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean addUser(User user);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<User> getAllUsers();

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    User getUserByEmail(String email);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(int id);

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean updateUser(User user);

    /**
     * Delete user boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteUser(int id);
}
