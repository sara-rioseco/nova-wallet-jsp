package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.TransactionDAO;
import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.shared.DB;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl extends DB implements TransactionDAO {

    public TransactionDAOImpl() {
        this.connect();
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        BigDecimal amount = transaction.getAmount();
        int currencyId = transaction.getCurrencyId();
        TransactionType transactionType = transaction.getTransactionType();
        int senderUserId = transaction.getSenderUserId();
        int senderAccountId = transaction.getSenderAccountId();
        int receiverUserId = transaction.getReceiverUserId();
        int receiverAccountId = transaction.getReceiverAccountId();
        Timestamp creationDate = transaction.getCreationDate();

        String sql= "INSERT INTO transactions(amount,currency_id,transaction_type,";
        sql+="sender_user_id,sender_account_id,receiver_user_id,receiver_account_id)";
        sql+=" VALUES("+amount+","+currencyId+","+transactionType+","+senderUserId+","
                +senderAccountId+","+receiverUserId+","+receiverAccountId+")";

        try {
            int res = update(sql);
            return res>0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        String sql="SELECT * FROM transactions";
        List<Transaction> list = new ArrayList<Transaction>();
        try (ResultSet rs = query(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                BigDecimal amount = rs.getBigDecimal("amount");
                int currencyId = rs.getInt("currency_id");
                TransactionType transactionType = TransactionType.valueOf(rs.getString("transaction_type"));
                int senderUserId = rs.getInt("sender_user_id");
                int senderAccountId = rs.getInt("sender_account_id");
                int receiverUserId = rs.getInt("receiver_user_id");
                int receiverAccountId = rs.getInt("receiver_account_id");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                Transaction transaction = new Transaction(id, amount, currencyId, transactionType, senderUserId, senderAccountId, receiverUserId, receiverAccountId, creationDate);
                list.add(transaction);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        String sql="SELECT * FROM transactions WHERE (sender_user_id="+userId+" OR receiver_user_id="+userId+")";
        List<Transaction> list = new ArrayList<Transaction>();
        try (ResultSet rs = query(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                BigDecimal amount = rs.getBigDecimal("amount");
                int currencyId = rs.getInt("currency_id");
                TransactionType transactionType = TransactionType.valueOf(rs.getString("transaction_type"));
                int senderUserId = rs.getInt("sender_user_id");
                int senderAccountId = rs.getInt("sender_account_id");
                int receiverUserId = rs.getInt("receiver_user_id");
                int receiverAccountId = rs.getInt("receiver_account_id");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                Transaction transaction = new Transaction(id, amount, currencyId, transactionType, senderUserId, senderAccountId, receiverUserId, receiverAccountId, creationDate);
                list.add(transaction);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        Transaction transaction = null;
        String sql="SELECT * FROM transactions WHERE id="+id;
        try (ResultSet rs = query(sql)) {
            while (rs.next()) {
                BigDecimal amount = rs.getBigDecimal("amount");
                int currencyId = rs.getInt("currency_id");
                TransactionType transactionType = TransactionType.valueOf(rs.getString("transaction_type"));
                int senderUserId = rs.getInt("sender_user_id");
                int senderAccountId = rs.getInt("sender_account_id");
                int receiverUserId = rs.getInt("receiver_user_id");
                int receiverAccountId = rs.getInt("receiver_account_id");
                Timestamp creationDate = rs.getTimestamp("creation_date");
                transaction = new Transaction(id, amount, currencyId, transactionType, senderUserId, senderAccountId, receiverUserId, receiverAccountId, creationDate);
            }
            return transaction;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
