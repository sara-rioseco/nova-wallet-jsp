package com.novawallet.model.service;

import com.novawallet.model.entity.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Create user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean createUser(User user);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(int id);

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    User getUserByEmail(String email);

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

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<User> getAllUsers();
}
