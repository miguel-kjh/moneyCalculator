package ispracticadeclase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
public class MoneyCalculator {
    
    
    public static void main(String[] args) throws IOException {
        System.out.println("Introduce Cantidad a cambiar: ");
        Scanner s = new Scanner(System.in);
        double amount = s.nextDouble(); 
        double exchangerate = getExchangeRate("USD", "EUR");
        System.out.println(amount + " Un dolar equivale a " + exchangerate*amount + " euros");
    }
    
    /**
     * Se apolla en la clase url para obterner la cadena Jason.
     * 
     * @param from
     * @param to
     * @return double
     * @throws IOException 
     */
    public static double getExchangeRate(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from + "_" + to  + "&compact=y");
        URLConnection connection = url.openConnection();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine(); // cadena Jason
            String line1 = line.substring(line.indexOf(to) + 12, line.indexOf("}")); // Operacion para obtener la subcadena
            return Double.parseDouble(line1);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1.;
    }   
    
    
}
