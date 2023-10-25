package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        addRandom(new Random(), lista);

        Nodo nodoRaiz = new Nodo(lista);
        nodoRaiz.start();

        try {
            nodoRaiz.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Lista Ordenada:");
        Nodo.mostrarLista(nodoRaiz.getLista());
    }

    private static void addRandom(Random random, List<Integer> lista) {
        int numRandomHasta = random.nextInt(100) + 1;
        for (int i = 0; i < numRandomHasta; i++) {
            int numRandom = random.nextInt(100) + 1;
            lista.add(numRandom);
        }
    }
}