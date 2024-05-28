package com.novawallet.model.service.impl;

import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.UserService;
import com.novawallet.shared.DB;

import java.util.List;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    /**
     * Instantiates a new User service.
     *
     * @param userDAO the user dao
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean createUser(User user) {
        if (user != null
                && user.getFirstName() !=null
                && !user.getFirstName().isBlank()
                && user.getLastName() !=null
                && !user.getLastName().isBlank()
                && user.getEmail() !=null
                && !user.getEmail().isBlank()
                && user.getPassword() !=null
                && !user.getPassword().isBlank()
                && userDAO.getUserByEmail(user.getEmail()) == null) {
            return userDAO.addUser(user);
        } else {
            System.out.println("Error creating user");
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public boolean updateUser(User user) {
        if (user != null
                && user.getFirstName() !=null
                && !user.getFirstName().isBlank()
                && user.getLastName() !=null
                && !user.getLastName().isBlank()
                && user.getPassword() !=null
                && !user.getPassword().isBlank()
                && userDAO.getUserById(user.getId()) != null) {
            return userDAO.updateUser(user);
        } else {
            System.out.println("Error updating user");
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        if (userDAO.getUserById(id) != null) {
            return userDAO.deleteUser(id);
        } else {
            System.out.println("Error deleting user");
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
