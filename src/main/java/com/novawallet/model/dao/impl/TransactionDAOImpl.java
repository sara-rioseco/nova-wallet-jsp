package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.TransactionDAO;
import com.novawallet.model.entity.Transaction;
import com.novawallet.shared.DB;

import java.util.List;

public class TransactionDAOImpl extends DB implements TransactionDAO {

    public TransactionDAOImpl() {
        this.connect();
    }

    @Override
    public int addTransaction(Transaction transaction) {
        return 0;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return List.of();
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int id) {
        return List.of();
    }

    @Override
    public Transaction getTransactionById(int id) {
        return null;
    }
}
