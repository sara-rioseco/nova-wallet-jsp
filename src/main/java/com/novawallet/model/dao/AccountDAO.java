package com.novawallet.model.dao;

import com.novawallet.model.entity.Account;
import java.util.List;

/**
 * The interface Account dao.
 */
public interface AccountDAO {
    /**
     * Add account boolean.
     *
     * @param account the account
     * @return the boolean
     */
    boolean addAccount(Account account);

    /**
     * Gets all accounts.
     *
     * @return the all accounts
     */
    List<Account> getAllAccounts();

    /**
     * Gets accounts by owner id.
     *
     * @param id the id
     * @return the accounts by owner id
     */
    List<Account> getAccountsByOwnerId(int id);

    /**
     * Gets account by id.
     *
     * @param id the id
     * @return the account by id
     */
    Account getAccountById(int id);

    /**
     * Update account boolean.
     *
     * @param account the account
     * @return the boolean
     */
    boolean updateAccount(Account account);

    /**
     * Delete account boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteAccount(int id);
}
