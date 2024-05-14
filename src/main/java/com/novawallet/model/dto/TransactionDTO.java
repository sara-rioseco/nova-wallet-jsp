package com.novawallet.model.dto;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.dao.impl.CurrencyDAOImpl;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.service.CurrencyService;
import com.novawallet.model.service.impl.CurrencyServiceImpl;


import java.text.NumberFormat;

import static com.novawallet.shared.Utils.*;

public class TransactionDTO {

    private final int id;
    private final String amount;
    private final String currency;
    private final String type;
    private final int senderUserId;
    private final int senderAccountId;
    private final int receiverUserId;
    private final int receiverAccountId;
    private final String date;


    public TransactionDTO(Transaction transaction) {
        CurrencyDAO currencyDAO = new CurrencyDAOImpl();
        CurrencyService currencyService = new CurrencyServiceImpl(currencyDAO);

        this.id = transaction.getId();
        this.amount = NumberFormat.getCurrencyInstance().format(transaction.getAmount());
        this.currency = currencyService.getCurrencyById(transaction.getCurrencyId()).getSymbol();
        this.type = capitalize(String.valueOf(transaction.getTransactionType()));
        this.senderUserId = transaction.getSenderUserId();
        this.senderAccountId = transaction.getSenderAccountId();
        this.receiverUserId = transaction.getReceiverUserId();
        this.receiverAccountId = transaction.getReceiverAccountId();
        this.date = "On " + formatDate(transaction.getCreationDate()) + " at " + formatTime(transaction.getCreationDate());
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
}
