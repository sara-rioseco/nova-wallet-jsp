package com.novawallet.model.service.impl;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.entity.Currency;
import com.novawallet.model.service.CurrencyService;

import java.util.List;

/**
 * The type Currency service.
 */
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDAO currencyDAO;

    /**
     * Instantiates a new Currency service.
     *
     * @param currencyDAO the currency dao
     */
    public CurrencyServiceImpl(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }
    @Override
    public boolean createCurrency(Currency currency) {
        if (currency != null
                && currency.getName() !=null
                && !currency.getName().isBlank()
                && currency.getSymbol() !=null
                && currency.getSymbol().length() == 3
                && !currency.getSymbol().isBlank()
                && currencyDAO.getCurrencyBySymbol(currency.getSymbol()) == null) {
            return currencyDAO.addCurrency(currency);
        } else {
            System.out.println("Error creating currency");
            return false;
        }
    }

    @Override
    public Currency getCurrencyById(int id) {
        return currencyDAO.getCurrencyById(id);
    }

    @Override
    public boolean updateCurrency(Currency currency) {
        if (currency != null
                && currency.getName() !=null
                && !currency.getName().isBlank()
                && currency.getSymbol() !=null
                && currency.getSymbol().length() == 3
                && !currency.getSymbol().isBlank()
                && currencyDAO.getCurrencyById(currency.getId()) != null) {
            return currencyDAO.updateCurrency(currency);
        } else {
            System.out.println("Error updating currency");
            return false;
        }
    }

    @Override
    public boolean deleteCurrency(int id) {
        if (currencyDAO.getCurrencyById(id) != null) {
            return currencyDAO.deleteCurrency(id);
        } else {
            System.out.println("Error deleting currency");
            return false;
        }
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDAO.getAllCurrencies();
    }
}
