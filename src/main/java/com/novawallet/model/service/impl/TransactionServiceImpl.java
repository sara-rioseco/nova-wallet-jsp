package com.novawallet.model.service.impl;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.dao.TransactionDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.dao.impl.AccountDAOImpl;
import com.novawallet.model.dao.impl.CurrencyDAOImpl;
import com.novawallet.model.dao.impl.UserDAOImpl;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.service.TransactionService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.novawallet.model.entity.TransactionType.*;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionDAO transactionDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public boolean createTransaction(Transaction transaction) {
        UserDAO userDAO = new UserDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        CurrencyDAO currencyDAO = new CurrencyDAOImpl();
        if (transaction != null
                && transaction.getAmount().compareTo(BigDecimal.ZERO) > 0
                && transaction.getTransactionType() != null
                && (transaction.getTransactionType() == deposit
                    || transaction.getTransactionType() == transfer
                    || transaction.getTransactionType() == withdrawal)
                && currencyDAO.getCurrencyById(transaction.getCurrencyId()) != null
                && userDAO.getUserById(transaction.getSenderUserId()) != null
                && accountDAO.getAccountById(transaction.getSenderAccountId()) != null
                && userDAO.getUserById(transaction.getReceiverUserId()) != null
                && accountDAO.getAccountById(transaction.getReceiverAccountId()) != null
                && transaction.getCreationDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
            return transactionDAO.addTransaction(transaction);
        } else {
            System.out.println("Error creating transaction");
            return false;
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactionDAO.getTransactionById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        return transactionDAO.getTransactionsByUserId(userId);
    }
}
