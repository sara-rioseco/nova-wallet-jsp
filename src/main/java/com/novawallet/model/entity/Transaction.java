package com.novawallet.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private BigDecimal amount;
    private int currencyId;
    private TransactionType transactionType;
    private int senderUserId;
    private int senderAccountId;
    private int receiverUserId;
    private int receiverAccountId;
    private Timestamp creationDate;

    public Transaction(BigDecimal amount, int currencyId, TransactionType transactionType,
                       int senderUserId, int senderAccountId, int receiverUserId,
                       int receiverAccountId) {
        this.amount = amount;
        this.currencyId = currencyId;
        this.transactionType = transactionType;
        this.senderUserId = senderUserId;
        this.senderAccountId = senderAccountId;
        this.receiverUserId = receiverUserId;
        this.receiverAccountId = receiverAccountId;
        this.creationDate = Timestamp.from(Instant.from(LocalDateTime.now()));
    }
}
