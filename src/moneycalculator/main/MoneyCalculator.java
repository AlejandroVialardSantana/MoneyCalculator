package moneycalculator.main;

import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.files.CurrencyLoaderFromFile;
import moneycalculator.persistence.rest.ExchangeRateLoaderFromWebService;
import moneycalculator.view.swing.MoneyCalculatorSwing;

public class MoneyCalculator {

    public static void main(String[] args) {
        
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("currency.txt");
        
        List<Currency> currenciesList = currencyLoaderFromFile.currencyLoader();
        
        for (Currency currency: currenciesList) System.out.println(currency.getCode() + " - " + currency.getName() + " - " + currency.getSymbol());
    
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService();
        
        for (Currency from: currenciesList) {
            for (Currency to: currenciesList) {
                if (!from.getCode().equals(to.getCode())) {
                    ExchangeRate exchangeRate = exchangeRateLoaderFromWebService.exchangerateLoader(from, to);
                    System.out.println("Rate from " + exchangeRate.getFrom().getCode() + " to " + exchangeRate.getTo().getCode() + ": " + exchangeRate.getRate());
                }
            }
        }  
        
        new MoneyCalculatorSwing(currenciesList, exchangeRateLoaderFromWebService);
    }
}
