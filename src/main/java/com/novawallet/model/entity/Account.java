package com.novawallet.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * The type Account.
 */
public class Account {
    private int id;
    private final int ownerId;
    private final int currencyId;
    private BigDecimal balance;
    private final Timestamp creationDate;

    /**
     * Instantiates a new Account.
     *
     * @param id           the id
     * @param ownerId      the owner id
     * @param currencyId   the currency id
     * @param balance      the balance
     * @param creationDate the creation date
     */
    public Account(int id, int ownerId, int currencyId, BigDecimal balance, Timestamp creationDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.currencyId = currencyId;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    /**
     * Instantiates a new Account.
     *
     * @param ownerId    the owner id
     * @param currencyId the currency id
     */
    public Account(int ownerId, int currencyId) {
        this(0, ownerId, currencyId, new BigDecimal(0), Timestamp.from(Instant.now()));
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
     * Gets owner id.
     *
     * @return the owner id
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Gets currency id.
     *
     * @return the currency id
     */
    public int getCurrencyId() {
        return currencyId;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Add balance.
     *
     * @param amount the amount
     */
    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    /**
     * Subtract balance.
     *
     * @param amount the amount
     */
    public void subtractBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
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
