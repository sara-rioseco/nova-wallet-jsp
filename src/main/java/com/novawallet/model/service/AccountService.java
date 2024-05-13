package com.novawallet.model.service;

import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.model.entity.User;

import java.util.List;

public interface AccountService {
    boolean createAccount(Account account);
    Account getAccountById(int id);
    boolean updateBalance(int id, String amt, TransactionType type);
    boolean deleteAccount(int id);
    List<Account> getAccountsByOwnerId(int id);
    List<Account> getAllAccounts();
}
