package com.novawallet.model.service.impl;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.model.service.AccountService;
import java.math.BigDecimal;
import java.util.List;

import static com.novawallet.model.entity.TransactionType.*;

public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public boolean createAccount(Account account) {
        if (account != null
                && account.getOwnerId() > 0
                && account.getCurrencyId() > 0
                && account.getBalance().compareTo(BigDecimal.ZERO) == 0
                ) {
            return accountDAO.addAccount(account);
        } else {
            System.out.println("Error creating account");
            return false;
        }
    }

    @Override
    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }

    @Override
    public boolean updateBalance(int id, BigDecimal amount, TransactionType type, boolean ownerUser) {
        Account account = accountDAO.getAccountById(id);
        if (id > 0
                && account != null
                && amount.compareTo(BigDecimal.ZERO) > 0
                && (type == deposit || type == withdrawal || type == transfer)) {
            if ((type == transfer && ownerUser)|| type == withdrawal) {
                account.subtractBalance(amount);
            }
            if((type == transfer && !ownerUser)|| type == deposit) {
                account.addBalance(amount);
            }
            return accountDAO.updateAccount(account);
        } else {
            System.out.println("Error updating balance");
            return false;
        }
    }

    @Override
    public boolean deleteAccount(int id) {
        if (accountDAO.getAccountById(id) != null) {
            return accountDAO.deleteAccount(id);
        } else {
            System.out.println("Error deleting account");
            return false;
        }
    }

    @Override
    public List<Account> getAccountsByOwnerId(int id) {
        return accountDAO.getAccountsByOwnerId(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
}
