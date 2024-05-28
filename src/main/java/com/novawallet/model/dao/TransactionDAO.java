package com.novawallet.model.dao;

import com.novawallet.model.entity.Transaction;
import java.util.List;

/**
 * The interface Transaction dao.
 */
public interface TransactionDAO {
    /**
     * Add transaction boolean.
     *
     * @param transaction the transaction
     * @return the boolean
     */
    boolean addTransaction(Transaction transaction);

    /**
     * Gets all transactions.
     *
     * @return the all transactions
     */
    List<Transaction> getAllTransactions();

    /**
     * Gets transactions by user id.
     *
     * @param id the id
     * @return the transactions by user id
     */
    List<Transaction> getTransactionsByUserId(int id);

    /**
     * Gets transaction by id.
     *
     * @param id the id
     * @return the transaction by id
     */
    Transaction getTransactionById(int id);
}
