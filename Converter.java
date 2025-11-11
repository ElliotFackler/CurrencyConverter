import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson; // Required for JSON parsing
import com.google.gson.reflect.TypeToken; // Helper for Gson



public class Converter {
    private static final String API_KEY = "e247acbf326ebef115b500f3";
    private static final String standardCurrency = "USD";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + standardCurrency;


    public static Map<String, Double> findRates() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL))
            .build();

        try {
            // 1. Send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 2. Check for success status (HTTP 200)
            if (response.statusCode() == 200) {
                return parseJsonRates(response.body());
            } else {
                System.err.println("API Error: Received status code " + response.statusCode());
                return Map.of(); // Return an empty map on API error
            }
        } catch (IOException e) {
            System.err.println("Network Error: Could not connect to API. Check internet connection.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Best practice for InterruptedExceptions
            System.err.println("Request was interrupted.");
        }
        return Map.of(); // Return empty map if exceptions occur
    }

    public static Map<String, Double> parseJsonRates(String jsonResponse) {
        Gson gson = new Gson();
        
        // This is a type-safe way to parse the entire response into a Java Map structure
        TypeToken<Map<String, Object>> mapType = new TypeToken<>() {};
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, mapType.getType());

        if (responseMap.containsKey("conversion_rates")) {
            // Gson typically parses the nested JSON object into a LinkedTreeMap, which 
            // is compatible with Map<String, Double> for conversion rates.
            // We use an explicit cast here.
            @SuppressWarnings("unchecked")
            Map<String, Double> rates = (Map<String, Double>) responseMap.get("conversion_rates");
            return rates;
        }
        return Map.of();

    }

    public static void main(String[] args) {
        String currencyFrom;
        String currencyTo;

        // Create the exchange rates map.
        Map<String, Double> exchangeRates = findRates();

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
        if (!currencyFrom.equals(standardCurrency) && currencyTo.equals(standardCurrency)) {
            endAmount = divide(amount, exchangeRates.get(currencyFrom));
        } else if (!currencyFrom.equals(standardCurrency) && !currencyTo.equals(standardCurrency)) {
            endAmount = divide(amount, exchangeRates.get(currencyFrom));
            endAmount = multiple(endAmount, exchangeRates.get(currencyTo));
        } else {
            endAmount = multiple(amount, exchangeRates.get(currencyTo));
        }

        // Return conversion result
        System.out.printf("Your %s %.2f is equivalent to %s %.2f", currencyFrom, amount, currencyTo, endAmount);
        
        return;
    }

    public static double divide(double num, double den) {
        return num / den;
    }

    public static double multiple(double num1, double num2) {
        return num1 * num2;
    }
}