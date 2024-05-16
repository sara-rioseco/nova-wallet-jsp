package com.novawallet.model.dao;

import com.novawallet.model.entity.Account;
import java.util.List;

public interface AccountDAO {
    boolean addAccount(Account account);
    List<Account> getAllAccounts();
    List<Account> getAccountsByOwnerId(int id);
    Account getAccountById(int id);
    boolean updateAccount(Account account);
    boolean deleteAccount(int id);
}
