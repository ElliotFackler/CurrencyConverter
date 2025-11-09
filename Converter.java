import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class Converter {
    public static void main(String[] args) {
        String currencyFrom;
        String currencyTo;
        String standardCurrency = "USD";

        // Create the exchange rates map.
        Map<String, Double> exchangeRates = Map.ofEntries(
          Map.entry("EUR", 0.86),
          Map.entry("JPY", 153.42),
          Map.entry("GBP", 0.76),
          Map.entry("CDR", 1.4),
          Map.entry("ARP", 1417.46),
          Map.entry("USD", 1.0)
        );

        // Set up input scanner and take in currency information.
        Scanner scanner = new Scanner(System.in);
            System.out.println("Enter currency code to convert from (EUR, GBP, JPY, USD):");
            currencyFrom = scanner.nextLine().toUpperCase();
            System.out.println("Enter the currency  code to convert to (EUR, GBP, JPY, USD):");
            currencyTo = scanner.nextLine().toUpperCase();
            System.out.println("Enter the amount to convert:");

                double amount = 0;
                if (scanner.hasNextDouble()) {
                    amount = scanner.nextDouble();
                } else {
                    scanner.close();
                    throw new InputMismatchException("That is not a valid amount.");
                }
                scanner.close();


                // Define the end value.
                double endAmount = 0;

                // Check if the entered currency code values are valid.
        if (!exchangeRates.containsKey(currencyFrom) || !exchangeRates.containsKey(currencyTo)) {
            // End program
            System.out.println("Invalid currency codes.");
            return;
        }


        // Get conversion
        if (currencyFrom != standardCurrency & currencyTo == standardCurrency) {
            endAmount = amount / exchangeRates.get(currencyTo);
        } else if (currencyFrom != standardCurrency && currencyTo != standardCurrency) {
            endAmount = amount / exchangeRates.get(currencyTo);
            endAmount = endAmount * exchangeRates.get(currencyFrom);
        } else if (currencyFrom == standardCurrency) {
            endAmount = amount * exchangeRates.get(currencyFrom);
        }

        

        // Return conversion result
        System.out.printf("Your %s %.2f is equivalent to %s %.2f", currencyFrom, amount, currencyTo, endAmount);
        
        return;
    }
}