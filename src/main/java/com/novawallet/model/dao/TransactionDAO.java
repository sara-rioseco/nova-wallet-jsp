package com.novawallet.model.dao;

import com.novawallet.model.entity.Contact;
import com.novawallet.model.entity.Transaction;

import java.util.List;

public interface TransactionDAO {
    int addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUserId(int id);
    Transaction getTransactionById(int id);
}
