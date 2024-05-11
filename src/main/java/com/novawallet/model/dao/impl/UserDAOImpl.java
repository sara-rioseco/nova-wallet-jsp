package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.entity.User;
import com.novawallet.shared.DB;

import java.sql.Timestamp;
import java.util.List;

public class UserDAOImpl extends DB implements UserDAO {

    public UserDAOImpl() {
        this.connect();
    }

    @Override
    public boolean addUser(User user) {
        String firstName= user.getFirstName();
        String lastName= user.getLastName();
        String email= user.getEmail();
        String password= user.getPassword();
        Timestamp creationDate= user.getCreationDate();

        String sql="INSERT INTO users(first_name,last_name,email,password,creation_date)";
        sql+=" VALUES('"+firstName+"','"+lastName+"','"+email+"','"+password+"',"+creationDate+")";

        int res = update(sql);
        return res>0;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean updateUser(int id, User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }
}
