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

    public int getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public int getSenderAccountId() {
        return senderAccountId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public String getDate() {
        return date;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }
}
