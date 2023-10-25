package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nodo extends Thread {
    private List<Integer> lista;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    public List<Integer> getLista() {
        return lista;
    }

    private void mezcla(List<Integer> lista, List<Integer> l1, List<Integer> l2) {
        int numPosicionL1 = 0, numPosicionL2 = 0;
        while (numPosicionL1 < l1.size() && numPosicionL2 < l2.size()) {
            if (l1.get(numPosicionL1) <= l2.get(numPosicionL2)) {
                lista.add(l1.get(numPosicionL1));
                numPosicionL1++;
            } else {
                lista.add(l2.get(numPosicionL2));
                numPosicionL2++;
            }
        }
        while (numPosicionL1 < l1.size()) {
            lista.add(l1.get(numPosicionL1));
            numPosicionL1++;
        }
        while (numPosicionL2 < l2.size()) {
            lista.add(l2.get(numPosicionL2));
            numPosicionL2++;
        }
    }

    @Override
    public void run() {
        if (lista.size() > 1) {
            int mitad = lista.size() / 2;

            List<Integer> l1 = new ArrayList<>(lista.subList(0, mitad));
            List<Integer> l2 = new ArrayList<>(lista.subList(mitad, lista.size()));

            Nodo nodoIzquierda = new Nodo(l1);
            Nodo nodoDerecha = new Nodo(l2);

            nodoIzquierda.start();
            nodoDerecha.start();

            try {
                nodoIzquierda.join();
                nodoDerecha.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lista.clear();
            mezcla(lista, nodoIzquierda.getLista(), nodoDerecha.getLista());
        }
    }

    public static void mostrarLista(List<Integer> lista) {
        for (Integer elemento : lista) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
}

