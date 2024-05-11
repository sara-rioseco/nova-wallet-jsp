package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.ContactDAO;
import com.novawallet.model.entity.Contact;
import com.novawallet.shared.DB;

import java.util.List;

public class ContactDAOImpl extends DB implements ContactDAO {

    public ContactDAOImpl() {
        this.connect();
    }

    @Override
    public int addContact(Contact contact) {
        return 0;
    }

    @Override
    public List<Contact> getAllContacts() {
        return List.of();
    }

    @Override
    public List<Contact> getContactsByOwnerId(int id) {
        return List.of();
    }

    @Override
    public Contact getContactById(int id) {
        return null;
    }

    @Override
    public int updateContact(int id, Contact contact) {
        return 0;
    }

    @Override
    public int deleteContact(int id) {
        return 0;
    }
}
