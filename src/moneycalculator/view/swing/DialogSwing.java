package moneycalculator.view.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.controller.MoneyCalculatorController;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.persistence.rest.ExchangeRateLoaderFromWebService;
import moneycalculator.view.Dialog;

public class DialogSwing extends JPanel implements Dialog {
    private final String CALCULATE_BUTTON_LABEL = "calculate";
    
    private final String MONEY_LABEL = "Money";
    private final String CURRENCY_FROM_LABEL = "Currency from";
    private final String CURRENCY_TO_LABEL = "Currency to";
    
    private final int MONEY_WIDTH = 40;
    private final int CURRENCY_FROM_WIDTH = 40;
    private final int CURRENCY_TO_WIDTH = 45;
    
    private JLabel moneyLabel, currencyFromLabel, currencyToLabel;
    private JTextField moneyField;
    private JComboBox<Currency> currencyFromComboBox;
    private JComboBox<Currency> currencyToComboBox;
    private JButton calculateButton;
    
    private List<Currency> currencies;
    private ExchangeRateLoaderFromWebService exchangerateLoaderFromWebService;
    private DisplaySwing moneyCalculatorDisplaySwing;
    
    private MoneyCalculatorController moneyCalculatorController;

    public DialogSwing(List<Currency> currencies, ExchangeRateLoaderFromWebService exchangerateLoaderFromWebService, DisplaySwing moneyCalculatorDisplaySwing) {
        this.currencies = currencies;
        this.exchangerateLoaderFromWebService = exchangerateLoaderFromWebService;
        this.moneyCalculatorDisplaySwing = moneyCalculatorDisplaySwing;
        
        this.moneyCalculatorController = new MoneyCalculatorController(this);
    
        createComponentGUI();
    }
    
    @Override
    public Money getMoney() {
        return new Money(Double.parseDouble(this.moneyField.getText()), (Currency) this.currencyFromComboBox.getSelectedItem());
    }
    
    @Override
    public Currency getCurrencyTo() {
        return (Currency) this.currencyToComboBox.getSelectedItem();
    }

    public ExchangeRateLoaderFromWebService getExchangeRateLoaderFromWebService() {
        return this.exchangerateLoaderFromWebService;
    }
    
    public DisplaySwing getMoneyCalculatorDisplaySwing() {
        return this.moneyCalculatorDisplaySwing;
    }
    
    private void createComponentGUI() {
        
        this.calculateButton = new JButton(this.CALCULATE_BUTTON_LABEL);
        
        this.moneyLabel = new JLabel(this.MONEY_LABEL);
        this.moneyField = new JTextField(this.MONEY_WIDTH);
        
        this.currencyFromLabel = new JLabel(this.CURRENCY_FROM_LABEL);
        this.currencyFromComboBox = new JComboBox<Currency>();
        this.currencyToLabel = new JLabel(this.CURRENCY_TO_LABEL);
        this.currencyToComboBox = new JComboBox<Currency>();
        
        for (Currency currency : this.currencies) {
            this.currencyFromComboBox.addItem(currency);
            this.currencyToComboBox.addItem(currency);
        }
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.X_AXIS));
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));
        
        labelPanel.add(this.moneyLabel);
        labelPanel.add(this.currencyFromLabel);
        labelPanel.add(this.currencyToLabel);
        
        dialogPanel.add(labelPanel);
        
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(0, 1));
        
        fieldPanel.add(this.moneyField);
        fieldPanel.add(this.currencyFromComboBox);
        fieldPanel.add(this.currencyToComboBox);
        
        dialogPanel.add(fieldPanel);
        
        JPanel controlPanel = new JPanel();
        
        controlPanel.add(this.calculateButton);
        
        this.calculateButton.addActionListener((ActionListener) this.moneyCalculatorController);
        
        setLayout(new BorderLayout());
        add(dialogPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);     
    }
}
