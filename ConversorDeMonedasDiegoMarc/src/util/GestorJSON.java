package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GestorJSON {
    private static final String ARCHIVO = "historial.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarConversion(double cantidad, String de, double resultado, String a) {
        Map<String, Object> registro = new HashMap<>();
        registro.put("fecha", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        registro.put("de", de);
        registro.put("a", a);
        registro.put("monto", cantidad);
        registro.put("resultado", resultado);

        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {
            gson.toJson(registro, writer);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            Impresor.mostrarError("No se pudo guardar en el historial: " + e.getMessage());
        }
    }
}