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
import com.novawallet.shared.DB;

import java.math.BigDecimal;
import java.util.List;

import static com.novawallet.model.entity.TransactionType.*;

/**
 * The type Transaction service.
 */
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDAO transactionDAO;
    private final DB db;

    /**
     * Instantiates a new Transaction service.
     *
     * @param transactionDAO the transaction dao
     * @param db             the db
     */
    public TransactionServiceImpl(TransactionDAO transactionDAO, DB db) {
        this.transactionDAO = transactionDAO;
        this.db = db;
    }

    @Override
    public boolean createTransaction(Transaction transaction) {
        UserDAO userDAO = new UserDAOImpl(db);
        AccountDAO accountDAO = new AccountDAOImpl(db);
        CurrencyDAO currencyDAO = new CurrencyDAOImpl(db);
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
                && accountDAO.getAccountById(transaction.getReceiverAccountId()) != null) {
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
