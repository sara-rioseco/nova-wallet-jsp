package com.novawallet.model.entity;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * The type Contact.
 */
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private final int contactUserId;
    private final int ownerUserId;
    private final Timestamp creationDate;

    /**
     * Instantiates a new Contact.
     *
     * @param id            the id
     * @param firstName     the first name
     * @param lastName      the last name
     * @param email         the email
     * @param contactUserId the contact user id
     * @param ownerUserId   the owner user id
     * @param creationDate  the creation date
     */
    public Contact(int id, String firstName, String lastName, String email, int contactUserId, int ownerUserId, Timestamp creationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactUserId = contactUserId;
        this.ownerUserId = ownerUserId;
        this.creationDate = creationDate;
    }

    /**
     * Instantiates a new Contact.
     *
     * @param firstName     the first name
     * @param email         the email
     * @param contactUserId the contact user id
     * @param ownerUserId   the owner user id
     */
    public Contact(String firstName, String email, int contactUserId, int ownerUserId) {
        this(0, firstName, null, email, contactUserId, ownerUserId, Timestamp.from(Instant.now()));
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get full name string.
     *
     * @return the string
     */
    public String getFullName(){
        return firstName + (lastName != null ? " " + lastName : "");
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Gets creation date.
     *
     * @return the creation date
     */
    public Timestamp getCreationDate() {
        return creationDate;
    }
}
