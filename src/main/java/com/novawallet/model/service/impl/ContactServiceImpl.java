package com.novawallet.model.service.impl;

import com.novawallet.model.dao.ContactDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.dao.impl.UserDAOImpl;
import com.novawallet.model.entity.Contact;
import com.novawallet.model.service.ContactService;
import com.novawallet.shared.DB;

import java.util.List;

/**
 * The type Contact service.
 */
public class ContactServiceImpl implements ContactService {

    private final ContactDAO contactDAO;
    private final DB db;

    /**
     * Instantiates a new Contact service.
     *
     * @param contactDAO the contact dao
     * @param db         the db
     */
    public ContactServiceImpl(ContactDAO contactDAO, DB db) {
        this.contactDAO = contactDAO;
        this.db = db;
    }

    @Override
    public boolean createContact(Contact contact) {
        UserDAO userDAO = new UserDAOImpl(db);
        if (contact != null
                && contact.getFirstName() !=null
                && !contact.getFirstName().isBlank()
                && contact.getEmail() !=null
                && !contact.getEmail().isBlank()
                && userDAO.getUserByEmail(contact.getEmail()) != null
                && userDAO.getUserById(contact.getContactUserId()) != null
                && userDAO.getUserById(contact.getOwnerUserId()) != null ) {
            return contactDAO.addContact(contact);
        } else {
            System.out.println("Error creating user");
            return false;
        }
    }

    @Override
    public Contact getContactById(int id) {
        return contactDAO.getContactById(id);
    }

    @Override
    public int getContactUserIdByContactId(int id) {
        return contactDAO.getContactUserIdByContactId(id);
    }

    @Override
    public boolean updateContact(Contact contact) {
        if (contact != null
                && contactDAO.getContactById(contact.getId()) != null
                && contact.getFirstName() !=null
                && !contact.getFirstName().isBlank()
                && contact.getEmail() !=null
                && !contact.getEmail().isBlank()) {
            return contactDAO.updateContact(contact);
        } else {
            System.out.println("Error updating contact");
            return false;
        }
    }

    @Override
    public boolean deleteContact(int id) {
        if (contactDAO.getContactById(id) != null) {
            return contactDAO.deleteContact(id);
        } else {
            System.out.println("Error deleting contact");
            return false;
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    @Override
    public List<Contact> getAllContactsByOwnerId(int ownerId) {
        return contactDAO.getContactsByOwnerId(ownerId);
    }
}
