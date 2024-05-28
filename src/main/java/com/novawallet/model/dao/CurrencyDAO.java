package com.novawallet.model.dao;

import com.novawallet.model.entity.Currency;
import java.util.List;

/**
 * The interface Currency dao.
 */
public interface CurrencyDAO {
    /**
     * Add currency boolean.
     *
     * @param currency the currency
     * @return the boolean
     */
    boolean addCurrency(Currency currency);

    /**
     * Gets all currencies.
     *
     * @return the all currencies
     */
    List<Currency> getAllCurrencies();

    /**
     * Gets currency by symbol.
     *
     * @param symbol the symbol
     * @return the currency by symbol
     */
    Currency getCurrencyBySymbol(String symbol);

    /**
     * Gets currency by id.
     *
     * @param id the id
     * @return the currency by id
     */
    Currency getCurrencyById(int id);

    /**
     * Update currency boolean.
     *
     * @param currency the currency
     * @return the boolean
     */
    boolean updateCurrency(Currency currency);

    /**
     * Delete currency boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteCurrency(int id);
}
