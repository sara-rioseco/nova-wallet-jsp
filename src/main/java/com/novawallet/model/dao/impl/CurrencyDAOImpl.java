package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.entity.Currency;
import com.novawallet.shared.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Currency dao.
 */
public class CurrencyDAOImpl implements CurrencyDAO {

    private final DB db;

    /**
     * Instantiates a new Currency dao.
     *
     * @param db the db
     */
    public CurrencyDAOImpl(DB db) {
        this.db = db;
    }

    @Override
    public boolean addCurrency(Currency currency) {
        String name= currency.getName();
        String symbol= currency.getSymbol();
        String sql="INSERT INTO currencies(name,symbol)";
        sql+=" VALUES('"+name+"','"+symbol+"')";
        try {
            int res = db.update(sql);
            return res>0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> getAllCurrencies() {
        String sql="SELECT * FROM currencies";
        List<Currency> list = new ArrayList<>();
        try (ResultSet rs = db.query(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String symbol = rs.getString("symbol");
                Currency currency = new Currency(id, name, symbol);
                list.add(currency);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error getting all currencies: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Currency getCurrencyBySymbol(String symbol) {
        Currency currency = null;
        String sql="SELECT * FROM currencies WHERE symbol='"+symbol+"'";
        try (ResultSet rs = db.query(sql)) {
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                currency = new Currency(id, name, symbol);
            }
            return currency;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Currency getCurrencyById(int id) {
        Currency currency = null;
        String sql="SELECT * FROM currencies WHERE id="+id;
        try (ResultSet rs = db.query(sql)) {
            while(rs.next()) {
                String name = rs.getString("name");
                String symbol = rs.getString("symbol");
                currency = new Currency(id, name, symbol);
            }
            return currency;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateCurrency(Currency currency) {
        int id = currency.getId();
        String name = currency.getName();
        String sql = "UPDATE currencies SET ";
        sql+="name='"+name+"', ";
        sql+=" WHERE id="+id;
        int res = db.update(sql);
        return res>0;
    }

    @Override
    public boolean deleteCurrency(int id) {
        String sql = "DELETE FROM currencies ";
        sql+=" WHERE id="+id;
        int res = db.update(sql);
        return res>0;
    }
}
