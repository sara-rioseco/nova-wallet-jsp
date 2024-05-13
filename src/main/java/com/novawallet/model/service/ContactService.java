package com.novawallet.model.service;

import com.novawallet.model.entity.Contact;

import java.util.List;

public interface ContactService {
    boolean createContact(Contact contact);
    Contact getContactById(int id);
    boolean updateContact(Contact contact);
    boolean deleteContact(int id);
    List<Contact> getAllContacts();
    List<Contact> getAllContactsByOwnerId(int ownerId);
}
