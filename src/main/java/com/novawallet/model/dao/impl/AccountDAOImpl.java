package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.entity.Account;
import com.novawallet.shared.DB;

import java.util.List;

public class AccountDAOImpl extends DB implements AccountDAO {

    public AccountDAOImpl() {
        this.connect();
    }

    @Override
    public int addAccount(Account account) {
        return 0;
    }

    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }

    @Override
    public List<Account> getAccountsByOwnerId(int id) {
        return List.of();
    }

    @Override
    public Account getAccountById(int id) {
        return null;
    }

    @Override
    public int updateAccount(int id, Account account) {
        return 0;
    }

    @Override
    public int deleteAccount(int id) {
        return 0;
    }
}
