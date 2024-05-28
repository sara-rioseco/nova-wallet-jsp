package com.novawallet.model.dto;

import com.novawallet.model.entity.Contact;

import static com.novawallet.shared.Utils.*;

/**
 * The type Contact dto.
 */
public class ContactDTO {

    private final int id;
    private final String name;
    private final String last;
    private final String mail;
    private final int currentUserId;
    private final int contactUserId;
    private final int ownerUserId;
    private final String date;

    /**
     * Instantiates a new Contact dto.
     *
     * @param contact       the contact
     * @param currentUserId the current user id
     */
    public ContactDTO(Contact contact, int currentUserId){
        this.currentUserId = currentUserId;
        this.id = contact.getId();
        this.name = capitalize(contact.getFirstName());
        this.last = capitalize(contact.getLastName());
        this.mail = contact.getEmail();
        this.contactUserId = contact.getContactUserId();
        this.ownerUserId = contact.getOwnerUserId();
        this.date = "On " + formatDate(contact.getCreationDate()) + " at " + formatTime(contact.getCreationDate());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Gets current user id.
     *
     * @return the current user id
     */
    public int getCurrentUserId() {
        return currentUserId;
    }

    /**
     * Gets contact user id.
     *
     * @return the contact user id
     */
    public int getContactUserId() {
        return contactUserId;
    }

    /**
     * Gets owner user id.
     *
     * @return the owner user id
     */
    public int getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }
}
