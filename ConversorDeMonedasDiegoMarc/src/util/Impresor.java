package util;

public class Impresor {
    public static void mostrarResultado(double cantidad, String de, double resultado, String a) {
        System.out.printf("\n>> %.2f %s = %.2f %s\n", cantidad, de, resultado, a);
    }

    public static void mostrarError(String mensaje) {
        System.err.println("[ERROR] " + mensaje);
    }
}