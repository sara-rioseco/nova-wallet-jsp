package com.novawallet.model.dao;

import com.novawallet.model.entity.Contact;
import com.novawallet.model.entity.User;

import java.util.List;

public interface ContactDAO {
    boolean addContact(Contact contact);
    List<Contact> getAllContacts();
    List<Contact> getContactsByOwnerId(int id);
    Contact getContactById(int id);
    boolean updateContact(Contact contact);
    boolean deleteContact(int id);
}
