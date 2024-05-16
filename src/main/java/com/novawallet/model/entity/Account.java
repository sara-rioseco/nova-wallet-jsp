package com.novawallet.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class Account {
    private int id;
    private final int ownerId;
    private final int currencyId;
    private BigDecimal balance;
    private final Timestamp creationDate;

    public Account(int id, int ownerId, int currencyId, BigDecimal balance, Timestamp creationDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.currencyId = currencyId;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public Account(int ownerId, int currencyId) {
        this(0, ownerId, currencyId, new BigDecimal(0), Timestamp.from(Instant.now()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void subtractBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }
}
