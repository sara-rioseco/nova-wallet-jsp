package com.novawallet.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * The type Transaction.
 */
public class Transaction {
    private int id;
    private BigDecimal amount;
    private int currencyId;
    private TransactionType transactionType;
    private int senderUserId;
    private int senderAccountId;
    private int receiverUserId;
    private int receiverAccountId;
    private final Timestamp creationDate;

    /**
     * Instantiates a new Transaction.
     *
     * @param id                the id
     * @param amount            the amount
     * @param currencyId        the currency id
     * @param transactionType   the transaction type
     * @param senderUserId      the sender user id
     * @param senderAccountId   the sender account id
     * @param receiverUserId    the receiver user id
     * @param receiverAccountId the receiver account id
     * @param creationDate      the creation date
     */
    public Transaction(int id, BigDecimal amount, int currencyId, TransactionType transactionType,
                       int senderUserId, int senderAccountId, int receiverUserId,
                       int receiverAccountId, Timestamp creationDate) {
        this.id = id;
        this.amount = amount;
        this.currencyId = currencyId;
        this.transactionType = transactionType;
        this.senderUserId = senderUserId;
        this.senderAccountId = senderAccountId;
        this.receiverUserId = receiverUserId;
        this.receiverAccountId = receiverAccountId;
        this.creationDate = creationDate;
    }

    /**
     * Instantiates a new Transaction.
     *
     * @param amount            the amount
     * @param currencyId        the currency id
     * @param transactionType   the transaction type
     * @param senderUserId      the sender user id
     * @param senderAccountId   the sender account id
     * @param receiverUserId    the receiver user id
     * @param receiverAccountId the receiver account id
     */
    public Transaction(BigDecimal amount, int currencyId, TransactionType transactionType,
                       int senderUserId, int senderAccountId, int receiverUserId,
                       int receiverAccountId) {
        this(0, amount, currencyId, transactionType, senderUserId, senderAccountId,
                receiverUserId, receiverAccountId, Timestamp.from(Instant.now()));
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
     * Gets amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * Sets currency id.
     *
     * @param currencyId the currency id
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Sets transaction type.
     *
     * @param transactionType the transaction type
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets sender user id.
     *
     * @return the sender user id
     */
    public int getSenderUserId() {
        return senderUserId;
    }

    /**
     * Sets sender user id.
     *
     * @param senderUserId the sender user id
     */
    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    /**
     * Gets sender account id.
     *
     * @return the sender account id
     */
    public int getSenderAccountId() {
        return senderAccountId;
    }

    /**
     * Sets sender account id.
     *
     * @param senderAccountId the sender account id
     */
    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    /**
     * Gets receiver account id.
     *
     * @return the receiver account id
     */
    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    /**
     * Sets receiver account id.
     *
     * @param receiverAccountId the receiver account id
     */
    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    /**
     * Gets receiver user id.
     *
     * @return the receiver user id
     */
    public int getReceiverUserId() {
        return receiverUserId;
    }

    /**
     * Sets receiver user id.
     *
     * @param receiverUserId the receiver user id
     */
    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
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
