Live Console Currency Converter

🌟 Overview

This is a Java console application designed to provide accurate, real-time currency conversions. It replaces hardcoded exchange rates with data fetched directly from the ExchangeRate-API, ensuring the calculations are based on the latest available market information. The application uses USD as the standard base currency for all conversions.

The project demonstrates core Java concepts including:

Modern HTTP networking (java.net.http).

External library integration (Gson for JSON parsing).

Robust error handling for network requests.

Basic console I/O and user interaction.

✨ Features

Live Rates: Fetches real-time exchange rates via an external API connection.

Base Currency Logic: Uses USD as the intermediate currency for all conversions (e.g., EUR -> USD -> JPY).

Network Resilience: Includes try-catch blocks to handle network errors (IOException, InterruptedException).

Dynamic Validation: Supports any currency code returned by the API, validating user input against the live data set.

Graceful Exit: Handles non-numeric input gracefully instead of crashing.

⚙️ Prerequisites

To compile and run this application, you need the following installed:

Java Development Kit (JDK) 11 or higher: Required for java.net.http.

Gson Library: An external library required for parsing the JSON data received from the API.

Including the Gson Library

Since this is a console application, you need to manually include the Gson JAR file in your classpath when compiling and running.

Download: Download the latest Gson JAR file (e.g., gson-2.10.1.jar) from the official repository or Maven Central.

Place: Save the downloaded JAR file in your project's root directory.

🚀 Setup and Run Instructions

Assuming the Gson JAR file (gson-2.10.1.jar) and your Converter.java file are in the same directory:

Step 1: Replace the API Key

Open Converter.java and replace the placeholder value with your actual API key from ExchangeRate-API (if you signed up for a service):

private static final String API_KEY = "e247acbf326ebef115b500f3"; // <--- Replace this


Step 2: Compile the Code

You must specify the Gson JAR file in the classpath when compiling:

javac -cp gson-2.10.1.jar Converter.java


This command generates the compiled bytecode file: Converter.class.

Step 3: Run the Application

Execute the compiled code, again including the Gson JAR in the classpath:

java -cp .:gson-2.10.1.jar Converter


(On Windows, replace the colon : with a semicolon ;)

💡 Usage

The application will first connect to the API. If successful, it will prompt you for input:

Supported Currencies: A list of supported currency codes will be displayed.

Currency FROM: Enter the 3-letter code you want to convert from (e.g., EUR).

Currency TO: Enter the 3-letter code you want to convert to (e.g., JPY).

Amount: Enter the numerical amount to convert (e.g., 100.50).

If the currency codes are valid and the amount is a number, the final conversion result will be printed.
