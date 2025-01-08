package proyectoconversor.com.conversor;
import java.io.IOException;

public class CurrencyConverter {

    private final ExchangeRateAPI exchangeRateAPI;

    public CurrencyConverter(ExchangeRateAPI exchangeRateAPI) {
        this.exchangeRateAPI = exchangeRateAPI;
    }

    public double convert(String baseCurrency, String targetCurrency, double amount) throws IOException, InterruptedException, IllegalArgumentException{
        if (amount < 0) {
            throw new IllegalArgumentException("La cantidad a convertir no puede ser negativa.");
        }

        if (baseCurrency == null || baseCurrency.isEmpty() || targetCurrency == null || targetCurrency.isEmpty()) {
            throw new IllegalArgumentException("Los códigos de moneda no pueden estar vacíos.");
        }

        if (baseCurrency.equals(targetCurrency)){
            return amount;
        }

        ExchangeRateAPI.ExchangeRateResponse response = exchangeRateAPI.getExchangeRate(baseCurrency, targetCurrency, amount);

        if (response == null) {
            throw new IOException("No se pudo obtener la respuesta de la API.");
        }

        return response.conversion_result();
    }
}
