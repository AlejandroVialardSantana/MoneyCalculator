package moneycalculator.persistence.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import moneycalculator.model.Currency;
import java.util.List;
import moneycalculator.persistence.CurrencyLoader;

public class CurrencyLoaderFromFile implements CurrencyLoader {
    
    private final String fileName;

    public CurrencyLoaderFromFile(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public List<Currency> currencyLoader() {
        List<Currency> currenciesList = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));
            IteratorReader iteratorReader = new IteratorReader(reader);
            for (String line: iteratorReader) currenciesList.add(currencyOf(line));     
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } 
        return currenciesList;
    }

    private Currency currencyOf(String line) {
        String[] split = line.split(", ");
        return new Currency(split[0], split[1], split[2]);
    }
    
}
