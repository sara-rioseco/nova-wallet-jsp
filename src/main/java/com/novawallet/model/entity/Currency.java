package com.novawallet.model.entity;

/**
 * The type Currency.
 */
public class Currency {
    private int id;
    private String name;
    private String symbol;

    /**
     * Instantiates a new Currency.
     *
     * @param id     the id
     * @param name   the name
     * @param symbol the symbol
     */
    public Currency(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Instantiates a new Currency.
     *
     * @param name   the name
     * @param symbol the symbol
     */
    public Currency(String name, String symbol) {
        this(0, name, symbol);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets symbol.
     *
     * @param symbol the symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
