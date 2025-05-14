package util;

import java.util.Scanner;

public class ValidadorEntrada {
    private static final Scanner scanner = new Scanner(System.in);

    public static int validarEntero(int min, int max) {
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    System.out.printf("Ingrese un número entre %d y %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    public static double validarMonto() {
        while (true) {
            try {
                double monto = Double.parseDouble(scanner.nextLine());
                if (monto > 0) {
                    return monto;
                } else {
                    System.out.print("El monto debe ser mayor a 0. Intente de nuevo: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    public static String validarMoneda(String mensaje, String[][] monedasDisponibles) {
        while (true) {
            System.out.print(mensaje);
            String sigla = scanner.nextLine().toUpperCase();

            for (String[] moneda : monedasDisponibles) {
                if (moneda[0].equals(sigla)) {
                    return sigla;
                }
            }
            System.out.println("Moneda no válida. Opciones:");
            for (String[] moneda : monedasDisponibles) {
                System.out.printf("- %s (%s)\n", moneda[1], moneda[0]);
            }
        }
    }
}