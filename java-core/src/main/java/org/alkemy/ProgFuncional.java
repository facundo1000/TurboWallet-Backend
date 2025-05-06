package org.alkemy;

import java.util.Arrays;
import java.util.List;

public class ProgFuncional {

    private static final List<String> NOMBRES = Arrays.asList("Ana", "Juan", "Pedro", "Alba", "Luis");

    private static final List<Integer> NUMEROS = Arrays.asList(1, 2, 3, 4, 5);


    public static void main(String[] args) {


        System.out.println("Nombres Filtrados y ordenados:");
        NOMBRES.stream()
                .filter(n -> n.startsWith("A"))        // Filtra los que empiezan con "A"
                .map(String::toUpperCase)                    // Los convierte a mayúsculas
                .sorted()                                    // Ordena alfabéticamente
                .forEach(System.out::println);               // Imprime cada uno


        System.out.println("-------------------------------------------");

        System.out.println("Imprime numeros pares");
        NUMEROS.stream()
                .filter(n -> n % 2 == 0) //filta aquellos numero que son pares
                .forEach(System.out::println); // Imprime cada uno

        System.out.println("-------------------------------------------");
        Integer reduce = NUMEROS.stream()
                .map(n -> n * n) // multiplica cada numero por si mismo
                .reduce(0, Integer::sum); // suma todos los numeros
        System.out.println("numero total: " + reduce);

    }

}
