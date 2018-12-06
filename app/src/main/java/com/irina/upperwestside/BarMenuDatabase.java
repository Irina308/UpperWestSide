package com.irina.upperwestside;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BarMenuDatabase {
    public BarMenuItem[] getBarMenuItems() {
        return MENU_ITEMS;
    }

    private final static BarMenuItem[] MENU_ITEMS = {
            new BarMenuItem("Fanta", "€", 1.0),
            new BarMenuItem("Becks", "€", 1.2),
            new BarMenuItem("Cola", "€", 130.02),
            new BarMenuItem("Eistee", "€", 1.9558),
            new BarMenuItem("Sprite", "€", 27.473),
            new BarMenuItem("Sprite", "€", 27.473),
            new BarMenuItem("Sprite", "€", 27.473),
            new BarMenuItem("Tee", "€", 13.1446)
    };

    private final static Map<String, BarMenuItem> CURRENCIES_MAP = new HashMap<>();

    private final static String[] CURRENCIES_LIST;

    static {
        for (BarMenuItem r : MENU_ITEMS) {
            CURRENCIES_MAP.put(r.getItemName(), r);
        }
        CURRENCIES_LIST = new String[CURRENCIES_MAP.size()];

        CURRENCIES_MAP.keySet().toArray(CURRENCIES_LIST);
        Arrays.sort(CURRENCIES_LIST);

    }

    /**
     * Returns list of currency names
     */

    public String[] getCurrencies() {
        return CURRENCIES_LIST;
    }

    /**
     * Gets exchange rate for currency (equivalent for one Euro)
     */

    public double getExchangeRate(String currency) {
        return CURRENCIES_MAP.get(currency).getItemPrice();
    }

    public String getCapital(String currency) {
        return CURRENCIES_MAP.get(currency).getCapital();
    }


    /**
     * Converts a value from a currency to another one
     * @return converted value
     */
    public double convert(double value, String currencyFrom, String currencyTo) {
        return value / getExchangeRate(currencyFrom) * getExchangeRate(currencyTo);
    }

    public void setExchangeRate(BarMenuItem updatedRate){
        for (BarMenuItem rate : MENU_ITEMS) {
            if (rate.getItemName().equals(updatedRate.getItemName())) {
                rate.setItemPrice(updatedRate.getItemPrice());
            }
        }

    }
}
