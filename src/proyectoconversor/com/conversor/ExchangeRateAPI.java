package proyectoconversor.com.conversor;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {

    private final String apiKey;

    public ExchangeRateAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public ExchangeRateResponse getExchangeRate(String baseCurrency, String targetCurrency, double amount) throws IOException, InterruptedException {
        String apiUrl = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                apiKey, baseCurrency, targetCurrency, amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener el tipo de cambio: Código de estado " + response.statusCode());
        }

        Gson gson = new Gson();
        ExchangeRateResponse fullResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

        // Creamos un nuevo objeto ExchangeRateResponse con solo los campos que necesitamos.
        return new ExchangeRateResponse(
                fullResponse.base_code(),
                fullResponse.target_code(),
                fullResponse.conversion_rate(),
                fullResponse.conversion_result()
        );
    }

    public record ExchangeRateResponse(String base_code, String target_code, double conversion_rate, double conversion_result) {}
}
