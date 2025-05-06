package org.alkemy;

import java.io.IOException;

public class ExcepcionesJava {
    public static void main(String[] args) {
        // 1. TRY - CATCH SIMPLE
        try {
            int resultado = 10 / 0;  // esto lanza ArithmeticException
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero.".concat(e.getMessage()));

        }

        // 2. MÚLTIPLES CATCH
        try {
            int[] numeros = {1, 2, 3};
//            int[] numeros = null;
            System.out.println(numeros[5]);  // Index fuera de rango
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera del arreglo.");
        } catch (Exception e) {
            System.out.println("Otro error: " + e.getMessage());
        }

        // 3. BLOQUE FINALLY
        try {
            String texto = null;
            System.out.println(texto.length());  // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Error: Variable no inicializada.");
        } finally {
            System.out.println("Este bloque siempre se ejecuta.");
        }

        // 4. USO DE THROW
        try {
            validarEdad(15);  // Lanza excepción si la edad < 18
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }

        // 5. USO DE THROWS
        try {
            puedeFallar();  // Método que puede lanzar IOException
        } catch (IOException e) {
            System.out.println("IOException manejada: " + e.getMessage());
        }
    }

    // Método que lanza una excepción con throw
    public static void validarEdad(int edad) {
        if (edad < 18) {
            throw new IllegalArgumentException("Debés ser mayor de edad.");
        }
        System.out.println("Edad válida: " + edad);
    }

    // Método que declara que puede lanzar una excepción
    public static void puedeFallar() throws IOException {
        throw new IOException("Error simulado de I/O.");
    }

}
