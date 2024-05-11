package com.novawallet.model.dao;

import com.novawallet.model.entity.User;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    List<User> getAllUsers();
    User getUserByEmail(String email);
    User getUserById(int id);
    boolean updateUser(int id, User user);
    boolean deleteUser(int id);
}
