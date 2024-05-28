package com.novawallet.model.service;

import com.novawallet.model.entity.Transaction;
import java.util.List;

/**
 * The interface Transaction service.
 */
public interface TransactionService {
    /**
     * Create transaction boolean.
     *
     * @param transaction the transaction
     * @return the boolean
     */
    boolean createTransaction(Transaction transaction);

    /**
     * Gets transaction by id.
     *
     * @param id the id
     * @return the transaction by id
     */
    Transaction getTransactionById(int id);

    /**
     * Gets all transactions.
     *
     * @return the all transactions
     */
    List<Transaction> getAllTransactions();

    /**
     * Gets transactions by user id.
     *
     * @param ownerId the owner id
     * @return the transactions by user id
     */
    List<Transaction> getTransactionsByUserId(int ownerId);
}
