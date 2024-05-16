package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.entity.Account;
import com.novawallet.shared.DB;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl extends DB implements AccountDAO {

    public AccountDAOImpl() {
        this.connect();
    }

    @Override
    public boolean addAccount(Account account) {
        int ownerId = account.getOwnerId();
        int currencyId = account.getCurrencyId();
        String sql="INSERT INTO accounts(owner_id, currency_id)";
        sql+=" VALUES("+ownerId+","+currencyId+")";
        try {
            int res = update(sql);
            return res>0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        String sql="SELECT * FROM accounts";
        List<Account> list = new ArrayList<Account>();
        try (ResultSet rs = query(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int ownerId = rs.getInt("owner_id");
                int currencyId = rs.getInt("currency_id");
                BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                Account account = new Account(id, ownerId, currencyId, balance, creationDate);
                list.add(account);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Account> getAccountsByOwnerId(int ownerId) {
        List<Account> list = new ArrayList<Account>();
        String sql="SELECT * FROM accounts WHERE owner_id="+ownerId;
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                int id = rs.getInt("id");
                int currencyId = rs.getInt("currency_id");
                BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                Account account = new Account(id, ownerId, currencyId, balance, creationDate);
                list.add(account);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Account getAccountById(int id) {
        Account account = null;
        String sql="SELECT * FROM accounts WHERE id="+id;
        try (ResultSet rs = query(sql)) {
            while(rs.next()) {
                int ownerId = rs.getInt("owner_id");
                int currencyId = rs.getInt("currency_id");
                BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                account = new Account(id, ownerId, currencyId, balance, creationDate);
            }
            return account;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        int id = account.getId();
        BigDecimal balance = account.getBalance();
        String sql = "UPDATE accounts SET ";
        sql+="balance="+balance;
        sql+=" WHERE id="+id;
        int res = update(sql);
        return res>0;
    }

    @Override
    public boolean deleteAccount(int id) {
        String sql = "DELETE FROM accounts ";
        sql+=" WHERE id="+id;
        int res = update(sql);
        return res>0;
    }
}
