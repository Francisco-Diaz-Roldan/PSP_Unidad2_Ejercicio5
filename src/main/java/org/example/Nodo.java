package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nodo extends Thread {
    private List<Integer> lista;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    private void mezcla(List<Integer> lista, List<Integer> l1, List<Integer> l2) {
        int posicionL1 = 0, posicionL2 = 0;
        while (posicionL1 < l1.size() && posicionL2 < l2.size()) {
            if (l1.get(posicionL1) <= l2.get(posicionL2)) {
                lista.add(l1.get(posicionL1));
                posicionL1++;
            } else {
                lista.add(l2.get(posicionL2));
                posicionL2++;
            }
        }
        while (posicionL1 < l1.size()) {
            lista.add(l1.get(posicionL1));
            posicionL1++;
        }
        while (posicionL2 < l2.size()) {
            lista.add(l2.get(posicionL2));
            posicionL2++;
        }
    }

    public List<Integer> getLista() {
        return lista;
    }

    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        addRandom(lista);
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

    private static void addRandom(List<Integer> lista) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            lista.add(random.nextInt(100));
        }
    }

    public static void mostrarLista(List<Integer> lista) {
        for (Integer elemento : lista) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
}

