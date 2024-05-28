package com.novawallet.model.dto;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.dao.impl.CurrencyDAOImpl;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.service.CurrencyService;
import com.novawallet.model.service.impl.CurrencyServiceImpl;
import com.novawallet.shared.DB;


import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import static com.novawallet.shared.Utils.*;

/**
 * The type Transaction dto.
 */
public class TransactionDTO {

    private int id;
    private String symbol;
    private String amount;
    private String currency;
    private String type;
    private int currentUserId;
    private int senderUserId;
    private int senderAccountId;
    private int receiverUserId;
    private int receiverAccountId;
    private String date;
    private CurrencyDAO currencyDAO;
    private CurrencyService currencyService;


    /**
     * Instantiates a new Transaction dto.
     *
     * @param transaction   the transaction
     * @param currentUserId the current user id
     * @param db            the db
     */
    public TransactionDTO(Transaction transaction, int currentUserId, DB db) {
        this.currencyDAO = new CurrencyDAOImpl(db);
        this.currencyService = new CurrencyServiceImpl(currencyDAO);
        this.id = transaction.getId();
        this.currentUserId = currentUserId;
        this.currency = currencyService.getCurrencyById(transaction.getCurrencyId()).getSymbol();
        this.amount = NumberFormat.getCurrencyInstance(Objects.equals(currency, "USD") ? Locale.US : null).format(transaction.getAmount());
        this.symbol = (Objects.equals(String.valueOf(transaction.getTransactionType()), "withdrawal")
                || (Objects.equals(String.valueOf(transaction.getTransactionType()), "transfer")
                    && currentUserId == transaction.getSenderUserId()))
                ? "-" : "";
        this.type = capitalize(String.valueOf(transaction.getTransactionType()));
        this.senderUserId = transaction.getSenderUserId();
        this.senderAccountId = transaction.getSenderAccountId();
        this.receiverUserId = transaction.getReceiverUserId();
        this.receiverAccountId = transaction.getReceiverAccountId();
        this.date = "On " + formatDate(transaction.getCreationDate()) + " at " + formatTime(transaction.getCreationDate());
    }

    /**
     * Instantiates a new Transaction dto.
     */
    public TransactionDTO() {
        this.id = 0;
        this.symbol = "";
        this.amount = "";
        this.currency = "";
        this.type = "";
        this.currentUserId = 0;
        this.senderUserId = 0;
        this.senderAccountId = 0;
        this.receiverUserId = 0;
        this.receiverAccountId = 0;
        this.date = "";
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
     * Gets amount.
     *
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
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
     * Gets sender account id.
     *
     * @return the sender account id
     */
    public int getSenderAccountId() {
        return senderAccountId;
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
     * Gets receiver account id.
     *
     * @return the receiver account id
     */
    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets current user id.
     *
     * @return the current user id
     */
    public int getCurrentUserId() {
        return currentUserId;
    }
}
