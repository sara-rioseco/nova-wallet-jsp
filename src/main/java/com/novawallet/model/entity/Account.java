package com.novawallet.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime; // maybe cambiar a java.sql.timestamp

public class Account {
    private int id;
    private int ownerId;
    private int currencyId;
    private BigDecimal balance;
    private final Timestamp creationDate;

    public Account(int ownerId, int currencyId) {
        this.ownerId = ownerId;
        this.currencyId = currencyId;
        this.balance = BigDecimal.ZERO;
        this.creationDate = Timestamp.from(Instant.from(LocalDateTime.now()));
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

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }
}
