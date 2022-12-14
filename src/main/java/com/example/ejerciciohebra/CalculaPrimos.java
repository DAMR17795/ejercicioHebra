package com.example.ejerciciohebra;

import javafx.scene.control.ProgressBar;

public class CalculaPrimos extends Thread {
    int min, max;
    ProgressBar barra1;
    private int contador;
    private boolean stopHilo= false;

    CalculaPrimos (int inf, int sup, ProgressBar barra) {
        min = inf;
        max = sup;
        barra1 = barra;
    }

    public void pararHilo() {
        stopHilo = true;
    }

    public static boolean esPrimo(int numero) {
        // El 0, 1 y 4 no son primos
        if (numero == 0 || numero == 1 || numero == 4) {
            return false;
        }
        for (int x = 2; x < numero / 2; x++) {
            // Si es divisible por cualquiera de estos números, no
            // es primo
            if (numero % x == 0)
                return false;
        }
        // Si no se pudo dividir por ninguno de los de arriba, sí es primo
        return true;
    }

    public void run () {
        for (int a=0; a<100; a++) {
            for (int i=0; i<= max; i++) {
                if (this.esPrimo(i)) {
                    //System.out.println("El numero " + i + " es primo");
                }
            }
            System.out.println("Contador: " + a);
            contador++;
            barra1.setProgress(barra1.getProgress()+0.01);
        }
    }

    public int getContador() {
        return contador;
    }

}
