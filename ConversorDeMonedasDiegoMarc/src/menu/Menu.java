package menu;

import servicios.ConversorMoneda;
import util.ValidadorEntrada;
import util.Impresor;
import util.GestorJSON;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String[][] MONEDAS = {
            {"USD", "Dólar Estadounidense"},
            {"EUR", "Euro"},
            {"JPY", "Yen Japonés"},
            {"GBP", "Libra Esterlina"},
            {"MXN", "Peso Mexicano"},
            {"CLP", "Peso Chileno"},
            {"CAD", "Dólar Canadiense"},
            {"AUD", "Dólar Australiano"}
    };

    public static void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("            \n=== BIENVENID@ ===");
            System.out.println("\n=== CONVERSOR DE MONEDAS DE DIEGO MARCIANO ===");
            System.out.println("1. Dólar Estadounidense (USD) → Euro (EUR)");
            System.out.println("2. Euro (EUR) → Dólar Estadounidense (USD)");
            System.out.println("3. Dólar Estadounidense (USD) → Peso Mexicano (MXN)");
            System.out.println("4. Peso Mexicano (MXN) → Dólar Estadounidense (USD)");
            System.out.println("5. Dólar Estadounidense (USD) → Libra Esterlina (GBP)");
            System.out.println("6. Libra Esterlina (GBP) → Dólar Estadounidense (USD)");
            System.out.println("7. Personalizado (elige cualquier moneda)");
            System.out.println("8. Salir");
            System.out.print("Elija una opción: ");

            int opcion = ValidadorEntrada.validarEntero(1, 8);
            if (opcion == 8) {
                System.out.println("¡Gracias por usar el conversor de Diego Marciano!");
                break;
            }

            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1 -> { monedaOrigen = "USD"; monedaDestino = "EUR"; }
                case 2 -> { monedaOrigen = "EUR"; monedaDestino = "USD"; }
                case 3 -> { monedaOrigen = "USD"; monedaDestino = "MXN"; }
                case 4 -> { monedaOrigen = "MXN"; monedaDestino = "USD"; }
                case 5 -> { monedaOrigen = "USD"; monedaDestino = "GBP"; }
                case 6 -> { monedaOrigen = "GBP"; monedaDestino = "USD"; }
                case 7 -> {
                    System.out.println("\nMonedas disponibles:");
                    for (String[] moneda : MONEDAS) {
                        System.out.printf("- %s (%s)\n", moneda[1], moneda[0]);
                    }
                    monedaOrigen = ValidadorEntrada.validarMoneda("Moneda origen (ej: USD): ", MONEDAS);
                    monedaDestino = ValidadorEntrada.validarMoneda("Moneda destino (ej: EUR): ", MONEDAS);
                }
            }

            System.out.print("Ingrese el monto a convertir: ");
            double cantidad = ValidadorEntrada.validarMonto();

            try {
                double resultado = ConversorMoneda.convertir(monedaOrigen, monedaDestino, cantidad);
                String nombreOrigen = obtenerNombreMoneda(monedaOrigen);
                String nombreDestino = obtenerNombreMoneda(monedaDestino);
                Impresor.mostrarResultado(cantidad, nombreOrigen + " (" + monedaOrigen + ")",
                        resultado, nombreDestino + " (" + monedaDestino + ")");
                GestorJSON.guardarConversion(cantidad, monedaOrigen, resultado, monedaDestino);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            System.out.print("¿Desea hacer otra conversión? (s/n): ");
            continuar = scanner.next().equalsIgnoreCase("s");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static String obtenerNombreMoneda(String sigla) {
        for (String[] moneda : MONEDAS) {
            if (moneda[0].equals(sigla)) {
                return moneda[1];
            }
        }
        return sigla;
    }
}