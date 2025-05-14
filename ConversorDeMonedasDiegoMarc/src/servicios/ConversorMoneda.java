package servicios;

import api.ConexionAPI;
import modelos.Moneda;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import util.Impresor;

public class ConversorMoneda {
    private static final Gson gson = new Gson();

    public static double convertir(String monedaOrigen, String monedaDestino, double cantidad)
            throws IllegalArgumentException, IllegalStateException {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        try {
            String jsonRespuesta = ConexionAPI.obtenerTasaCambio(monedaOrigen, monedaDestino);
            Moneda moneda = gson.fromJson(jsonRespuesta, Moneda.class);
            return cantidad * moneda.getTasaConversion();

        } catch (JsonSyntaxException e) {
            Impresor.mostrarError("La API devolvió un JSON inválido");
            throw new IllegalStateException("Error en el formato de la respuesta: " + e.getMessage());
        } catch (Exception e) {
            Impresor.mostrarError("Fallo en la conversión: " + e.getMessage());
            throw new IllegalStateException("Error al conectar con la API");
        }
    }
}