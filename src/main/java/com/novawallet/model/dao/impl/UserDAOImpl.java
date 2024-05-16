package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.entity.User;
import com.novawallet.shared.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        String sql="INSERT INTO users(first_name,last_name,email,password)";
        sql+=" VALUES('"+firstName+"','"+lastName+"','"+email+"','"+password+"')";
        try {
            int res = update(sql);
            return res>0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql="SELECT * FROM users";
        List<User> list = new ArrayList<User>();
        try (ResultSet rs = query(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                User user = new User(id, firstName, lastName, email, password, creationDate);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        String sql="SELECT * FROM users WHERE email='"+email+"'";
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                user = new User(id, firstName, lastName, email, password, creationDate);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String sql="SELECT * FROM users WHERE id="+id;
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                user = new User(id, firstName, lastName, email, password, creationDate);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        int id= user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String sql = "UPDATE users SET ";
        sql+="first_name='"+firstName+"', last_name='"+lastName+"', ";
        sql+="password='"+password+"', WHERE id="+id;
        int res = update(sql);
        return res>0;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users ";
        sql+=" WHERE id="+id;
        int res = update(sql);
        return res>0;
    }
}
