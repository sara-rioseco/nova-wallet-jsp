package com.novawallet.model.service;

import com.novawallet.model.entity.User;

import java.util.List;

public interface UserService {
    boolean createUser(User user);
    User getUserById(int id);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> getAllUsers();
}
