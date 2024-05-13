package com.novawallet.model.service;

import com.novawallet.model.entity.Transaction;

import java.util.List;

public interface TransactionService {
    boolean createTransaction(Transaction transaction);
    Transaction getTransactionById(int id);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUserId(int ownerId);
}
