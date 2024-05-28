package com.novawallet.model.dao;

import com.novawallet.model.entity.Contact;
import java.util.List;

/**
 * The interface Contact dao.
 */
public interface ContactDAO {
    /**
     * Add contact boolean.
     *
     * @param contact the contact
     * @return the boolean
     */
    boolean addContact(Contact contact);

    /**
     * Gets all contacts.
     *
     * @return the all contacts
     */
    List<Contact> getAllContacts();

    /**
     * Gets contacts by owner id.
     *
     * @param id the id
     * @return the contacts by owner id
     */
    List<Contact> getContactsByOwnerId(int id);

    /**
     * Gets contact user id by contact id.
     *
     * @param id the id
     * @return the contact user id by contact id
     */
    int getContactUserIdByContactId(int id);

    /**
     * Gets contact by id.
     *
     * @param id the id
     * @return the contact by id
     */
    Contact getContactById(int id);

    /**
     * Update contact boolean.
     *
     * @param contact the contact
     * @return the boolean
     */
    boolean updateContact(Contact contact);

    /**
     * Delete contact boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteContact(int id);
}
