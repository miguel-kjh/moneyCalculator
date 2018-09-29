package ispracticadeclase;

import moneyCalculator_Model.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception {
      MoneyCalculator moneyCalculator = new MoneyCalculator();
      moneyCalculator.execute();       
    }

    private final CurrencyList list;
    private Money money;
    private Currency currencyTo;
    private ExchangeRate exchangeRate;

    public MoneyCalculator() {
        list = new CurrencyList();
    }
       
    private void execute() throws Exception{
        input();
        process();
        output();
    }

    private void input(){
        System.out.println("Introduzca cantidad");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        
        while (true) {
            System.out.println("Introduzca código divisa origen");
            Currency currency = list.get(scanner.next());
            if(currency != null){
                money = new Money(amount, currency);break;
            } else {
                System.out.println("Divisa no reconocida");
            }
        }  
        while (true) {
            System.out.println("Introduzca código divisa destino");
            currencyTo = list.get(scanner.next());
            if(currencyTo != null)break;
            else System.out.println("Divisa no reconocida");
        }
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(money.getCurrency(), currencyTo);
    }
    
    private void output(){
        System.out.println(money.getAmount() + money.getCurrency().getSymbol() 
                + " equivalen a " + money.getAmount() * exchangeRate.getRate() 
                + currencyTo.getSymbol());

    }
    
    private static ExchangeRate  getExchangeRate(Currency from, Currency to) throws Exception{
      URL url = 
            new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" +
                    from.getCode() + "_" + to.getCode() + "&compact=y");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to.getCode())+12, line.indexOf("}"));
            return new ExchangeRate(new Date(),from, to, Double.parseDouble(line1));

      }
    }
}
