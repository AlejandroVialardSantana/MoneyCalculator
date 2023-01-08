package moneycalculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.view.swing.DialogSwing;

public class MoneyCalculatorController implements ActionListener {
    
    private final DialogSwing dialogSwing;

    public MoneyCalculatorController(DialogSwing dialogSwing) {
        this.dialogSwing = dialogSwing;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Money money = this.dialogSwing.getMoney();
        Currency currencyFrom = money.getCurrency();
        Currency currencyTo = this.dialogSwing.getCurrencyTo();
        ExchangeRate exchangeRate = this.dialogSwing.getExchangeRateLoaderFromWebService().exchangerateLoader(currencyFrom, currencyTo);
        
        this.dialogSwing.getMoneyCalculatorDisplaySwing().refreshMoney(new Money(exchangeRate.getRate() * money.getAmount(), currencyTo));
    }   
}
