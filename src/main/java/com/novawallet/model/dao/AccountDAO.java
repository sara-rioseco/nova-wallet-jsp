package com.novawallet.model.dao;

import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.User;

import java.util.List;

public interface AccountDAO {
    int addAccount(Account account);
    List<Account> getAllAccounts();
    List<Account> getAccountsByOwnerId(int id);
    Account getAccountById(int id);
    int updateAccount(int id, Account account);
    int deleteAccount(int id);
}
