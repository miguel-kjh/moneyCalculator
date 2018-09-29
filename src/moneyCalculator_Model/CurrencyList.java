package moneyCalculator_Model;

import java.util.HashMap;
import java.util.Map;

public class CurrencyList {
    private final Map<String, Currency> currencies = new HashMap<>();

    public CurrencyList() {
        currencies.put("USD", new Currency("USD","Dolar americano","$"));
        currencies.put("EUR", new Currency("EUR","Euro","€"));  
    }
    
    public boolean add(String name, Currency currency){
        return currencies.put(name, currency) != null;
    }
    
    public Currency get(String code) {
        return currencies.get(code.toUpperCase());
    }
    
   

}
