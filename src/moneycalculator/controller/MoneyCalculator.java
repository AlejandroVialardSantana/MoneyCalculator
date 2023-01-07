package moneycalculator.controller;

import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.persistence.files.CurrencyLoaderFromFile;

public class MoneyCalculator {

    public static void main(String[] args) {
        
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("currency.txt");
        
        List<Currency> currenciesList = currencyLoaderFromFile.currencyLoader();
        
        for (Currency currency: currenciesList) System.out.println(currency.getCode());
        
    }
    
}
