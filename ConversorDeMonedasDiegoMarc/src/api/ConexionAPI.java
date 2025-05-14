package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionAPI {
    private static final String API_KEY = "8e34d44f5c5bb3cb90ffd68b";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static String obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws Exception {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + monedaOrigen + "/" + monedaDestino))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        if (respuesta.statusCode() != 200) {
            throw new RuntimeException("Error en la API: " + respuesta.body());
        }
        return respuesta.body();
    }
}