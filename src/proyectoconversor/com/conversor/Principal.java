package proyectoconversor.com.conversor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Principal {
    private static List<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        String apiKey = System.getenv("EXCHANGE_RATE_APIKEY");

        if (apiKey == null) {
            System.err.println("Error: La variable de entorno EXCHANGE_RATE_API_KEY no está definida.");
            System.exit(1);
            return;
        }

        try {
            ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI(apiKey);
            CurrencyConverter converter = new CurrencyConverter(exchangeRateAPI);
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                mostrarMenu();
                System.out.print("Ingrese una opción: ");
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Error: Ingrese un número entero válido.");
                    scanner.nextLine();
                    opcion = -1; // Valor para indicar error en la entrada
                    continue; // Vuelve al inicio del bucle
                }

                if (opcion >= 1 && opcion <= 12) {
                    convertirMoneda(scanner, converter, opcion);
                } else if (opcion == 14) { //Opcion para ver el historial
                    mostrarHistorial();
                } else if (opcion != 13 && opcion != -1) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }

            } while (opcion != 13);

            System.out.println("Gracias por usar el conversor de monedas.");
            scanner.close();

        } catch (IOException e) {
            System.err.println("Error de IO: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Operación interrumpida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en los argumentos ingresados: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("********************************");
        System.out.println("\n**** CONVERSOR DE MONEDAS ****");
        System.out.println("""
                --- Monedas disponibles: ---
                ARS - Peso argentino | BOB - Boliviano boliviano
                BRL - Real brasileño | CLP - Peso chileno
                COP - Peso colombiano| MXN - Peso mexicano
                USD - Dólar estadounidense\n""");
        System.out.println("1. ARS a USD");
        System.out.println("2. USD a ARS");
        System.out.println("3. BOB a USD");
        System.out.println("4. USD a BOB");
        System.out.println("5. BRL a USD");
        System.out.println("6. USD a BRL");
        System.out.println("7. CLP a USD");
        System.out.println("8. USD a CLP");
        System.out.println("9. COP a USD");
        System.out.println("10. USD a COP");
        System.out.println("11. MXN a USD");
        System.out.println("12. USD a MXN");
        System.out.println("13. Salir");
        System.out.println("14. Ver Historial");
        System.out.println("********************************");
    }

    private static void convertirMoneda(Scanner scanner, CurrencyConverter converter, int opcion) throws IOException, InterruptedException {
        String baseCurrency = "";
        String targetCurrency = "";

        switch (opcion) {
            case 1:
                baseCurrency = "ARS";
                targetCurrency = "USD";
                break;
            case 2:
                baseCurrency = "USD";
                targetCurrency = "ARS";
                break;
            case 3:
                baseCurrency = "BOB";
                targetCurrency = "USD";
                break;
            case 4:
                baseCurrency = "USD";
                targetCurrency = "BOB";
                break;
            case 5:
                baseCurrency = "BRL";
                targetCurrency = "USD";
                break;
            case 6:
                baseCurrency = "USD";
                targetCurrency = "BRL";
                break;
            case 7:
                baseCurrency = "CLP";
                targetCurrency = "USD";
                break;
            case 8:
                baseCurrency = "USD";
                targetCurrency = "CLP";
                break;
            case 9:
                baseCurrency = "COP";
                targetCurrency = "USD";
                break;
            case 10:
                baseCurrency = "USD";
                targetCurrency = "COP";
                break;
            case 11:
                baseCurrency = "MXN";
                targetCurrency = "USD";
                break;
            case 12:
                baseCurrency = "USD";
                targetCurrency = "MXN";
                break;
        }

        try {
            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            double convertedAmount = converter.convert(baseCurrency, targetCurrency, amount);
            String conversion = amount + " " + baseCurrency + " son " + convertedAmount + " " + targetCurrency;
            System.out.println(conversion);
            historial.add(conversion);
        } catch (InputMismatchException e) {
            System.err.println("Error: Ingrese un número válido para la cantidad.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
        } else {
            System.out.println("\n--- Historial de Conversiones ---");
            for (String conversion : historial) {
                System.out.println(conversion);
            }
        }
    }
}