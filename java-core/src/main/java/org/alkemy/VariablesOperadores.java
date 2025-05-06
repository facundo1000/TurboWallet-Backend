package org.alkemy;


public class VariablesOperadores {
    public static void main(String[] args) {
        // TIPOS DE DATOS Y VARIABLES
        int numeroEntero = 10;
        double numeroDecimal = 5.5;
        boolean esJavaDivertido = true;
        char letra = 'J';
        String texto = "Hola Java";

        System.out.println("Número entero: " + numeroEntero);
        System.out.println("Número decimal: " + numeroDecimal);
        System.out.println("¿Es Peter Parker es el hombre araña? " + esJavaDivertido);
        System.out.println("Letra: " + letra);
        System.out.println("Texto: " + texto);

        // OPERADORES
        int suma = numeroEntero + 5;
        int resta = numeroEntero - 3;
        int multiplicacion = numeroEntero * 2;
        double division = numeroEntero / 3.0;
        int modulo = numeroEntero % 3;

        System.out.println("\nOperadores:");
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicación: " + multiplicacion);
        System.out.println("División: " + division);
        System.out.println("Módulo: " + modulo);

        // ESTRUCTURAS DE CONTROL - IF
        System.out.println("\nEstructura if:");
        if (numeroEntero > 5) {
            System.out.println("El número es mayor que 5");
        } else {
            System.out.println("El número es 5 o menor");
        }

        // ESTRUCTURA SWITCH
        System.out.println("\nEstructura switch:");
        int dia = 3;
        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miércoles");
                break;
            default:
                System.out.println("Otro día menos");
        }

        // FOR
        System.out.println("\nBucle for:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Lloros del dia = " + i);
        }

        // WHILE
        System.out.println("\nBucle while:");
        int contador = 0;
        while (contador < 3) {
            System.out.println("Contador de peluquines: " + contador);
            contador++;
        }

        // DO-WHILE
        System.out.println("\nBucle do-while:");
        int j = 0;
        do {
            System.out.println("j = " + j);
            j++;
        } while (j < 2);

    }
}