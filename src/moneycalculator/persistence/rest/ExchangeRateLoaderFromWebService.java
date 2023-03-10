package moneycalculator.persistence.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

public class ExchangeRateLoaderFromWebService implements ExchangeRateLoader {
    
    @Override
    public ExchangeRate exchangerateLoader(Currency from, Currency to) {
        try {
            return new ExchangeRate(read(from.getCode(), to.getCode()), from, to);
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    private Double read(String codeFrom, String codeTo) throws MalformedURLException, IOException {
        String line = read(new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + codeFrom.toLowerCase() + "/" + codeTo.toLowerCase() + ".json"));
        return Double.parseDouble(getStringRateFromJSONLine(line));
    }

    private String getStringRateFromJSONLine(String line) {
        String[] split = line.split(",");
        return split[1].substring(split[1].indexOf(":") + 1, split[1].indexOf("}") - 1);
    }
    
     private String read(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[1024];
        int lenght = inputStream.read(buffer);
        return new String(buffer, 0, lenght);
    }
}
