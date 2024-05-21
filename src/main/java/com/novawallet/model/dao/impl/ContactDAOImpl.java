package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.ContactDAO;
import com.novawallet.model.entity.Contact;
import com.novawallet.shared.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl extends DB implements ContactDAO {

    public ContactDAOImpl() {
        if(this.stmt == null) {
            this.connect();
        }
    }

    @Override
    public boolean addContact(Contact contact) {
        String firstName= contact.getFirstName();
        String lastName= contact.getLastName();
        String email= contact.getEmail();
        int contactUserId= contact.getContactUserId();
        int ownerUserId= contact.getOwnerUserId();

        String sql="INSERT INTO contacts(first_name,last_name,email,contact_user_id,owner_user_id)";
        sql+=" VALUES('"+firstName+"','"+lastName+"','"+email+"',"+contactUserId+","
                +ownerUserId+")";
        try {
            int res = update(sql);
            return res>0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        String sql="SELECT * FROM contacts";
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int contactUserId = rs.getInt("contact_user_id");
                int ownerUserId = rs.getInt("owner_user_id");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                Contact contact = new Contact(id, firstName, lastName, email, contactUserId, ownerUserId, creationDate);
                list.add(contact);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Contact> getContactsByOwnerId(int ownerUserId) {
        List<Contact> list = new ArrayList<>();
        String sql="SELECT * FROM contacts WHERE owner_user_id="+ownerUserId;
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int contactUserId = rs.getInt("contact_user_id");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                Contact contact = new Contact(id, firstName, lastName, email, contactUserId, ownerUserId, creationDate);
                list.add(contact);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getContactUserIdByContactId(int id) {
        return getContactById(id).getContactUserId();
    }

    @Override
    public Contact getContactById(int id) {
        Contact contact = null;
        String sql="SELECT * FROM contacts WHERE id="+id;
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int contactUserId = rs.getInt("contact_user_id");
                int ownerUserId = rs.getInt("owner_user_id");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                contact = new Contact(id, firstName, lastName, email, contactUserId, ownerUserId, creationDate);
            }
            return contact;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateContact(Contact contact) {
        int id = contact.getId();
        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String email = contact.getEmail();
        String sql = "UPDATE contacts SET first_name='"+firstName+"', ";
        sql+="last_name='"+lastName+"', email='"+email+"', ";
        sql+=" WHERE id="+id;
        int res = update(sql);
        return res>0;
    }

    @Override
    public boolean deleteContact(int id) {
        String sql = "DELETE FROM contacts ";
        sql+=" WHERE id="+id;
        int res = update(sql);
        return res>0;
    }
}
