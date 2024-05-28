package com.novawallet.model.service;

import com.novawallet.model.entity.Currency;
import java.util.List;

/**
 * The interface Currency service.
 */
public interface CurrencyService {
    /**
     * Create currency boolean.
     *
     * @param currency the currency
     * @return the boolean
     */
    boolean createCurrency(Currency currency);

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

    /**
     * Gets all currencies.
     *
     * @return the all currencies
     */
    List<Currency> getAllCurrencies();
}
