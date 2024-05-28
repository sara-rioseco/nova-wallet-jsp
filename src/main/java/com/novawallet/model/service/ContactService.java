package com.novawallet.model.service;

import com.novawallet.model.entity.Contact;
import java.util.List;

/**
 * The interface Contact service.
 */
public interface ContactService {
    /**
     * Create contact boolean.
     *
     * @param contact the contact
     * @return the boolean
     */
    boolean createContact(Contact contact);

    /**
     * Gets contact by id.
     *
     * @param id the id
     * @return the contact by id
     */
    Contact getContactById(int id);

    /**
     * Gets contact user id by contact id.
     *
     * @param id the id
     * @return the contact user id by contact id
     */
    int getContactUserIdByContactId(int id);

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

    /**
     * Gets all contacts.
     *
     * @return the all contacts
     */
    List<Contact> getAllContacts();

    /**
     * Gets all contacts by owner id.
     *
     * @param ownerId the owner id
     * @return the all contacts by owner id
     */
    List<Contact> getAllContactsByOwnerId(int ownerId);
}
