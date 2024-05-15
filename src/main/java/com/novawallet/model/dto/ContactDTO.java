package com.novawallet.model.dto;

import com.novawallet.model.entity.Contact;

import static com.novawallet.shared.Utils.*;

public class ContactDTO {

    private final int id;
    private final String name;
    private final String last;
    private final String mail;
    private final int currentUserId;
    private final int contactUserId;
    private final int ownerUserId;
    private final String date;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast() {
        return last;
    }

    public String getMail() {
        return mail;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public int getContactUserId() {
        return contactUserId;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public String getDate() {
        return date;
    }
}
