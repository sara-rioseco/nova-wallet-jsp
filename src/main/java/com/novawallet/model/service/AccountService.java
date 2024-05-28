package com.novawallet.model.service;

import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.TransactionType;
import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Account service.
 */
public interface AccountService {
    /**
     * Create account boolean.
     *
     * @param account the account
     * @return the boolean
     */
    boolean createAccount(Account account);

    /**
     * Gets account by id.
     *
     * @param id the id
     * @return the account by id
     */
    Account getAccountById(int id);

    /**
     * Update balance boolean.
     *
     * @param id        the id
     * @param amount    the amount
     * @param type      the type
     * @param ownerUser the owner user
     * @return the boolean
     */
    boolean updateBalance(int id, BigDecimal amount, TransactionType type, boolean ownerUser);

    /**
     * Delete account boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteAccount(int id);

    /**
     * Gets accounts by owner id.
     *
     * @param id the id
     * @return the accounts by owner id
     */
    List<Account> getAccountsByOwnerId(int id);

    /**
     * Gets all accounts.
     *
     * @return the all accounts
     */
    List<Account> getAllAccounts();
}
