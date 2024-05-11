package com.novawallet.model.dao;

import com.novawallet.model.entity.Contact;
import com.novawallet.model.entity.User;

import java.util.List;

public interface ContactDAO {
    int addContact(Contact contact);
    List<Contact> getAllContacts();
    List<Contact> getContactsByOwnerId(int id);
    Contact getContactById(int id);
    int updateContact(int id, Contact contact);
    int deleteContact(int id);
}
