package com.example.ejerciciohebra;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class CalculaPrimos extends Thread {
    int min, max;
    ProgressBar barra1;
    private int contador;
    private boolean stopHilo= false;

    private Label ganador;
    private String name;

    private Label labelPorcentaje;

    CalculaPrimos (int inf, int sup, ProgressBar barra, Label resultado, String nombre, Label porcentaje) {
        min = inf;
        max = sup;
        barra1 = barra;
        ganador = resultado;
        name = nombre;
        labelPorcentaje = porcentaje;
    }

    public void pararHilo() {
        stopHilo = true;
        if (stopHilo==true) {
            System.out.println("Ha llegado: " + name);
            interrupt();
        }
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
        barra1.setProgress(0.0);
        contador=1;
        try {
            for (int a=1; a<=100; a++) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        labelPorcentaje.setText(String.valueOf(contador));
                    }
                });
                for (int i=0; i<= max; i++) {
                    if (this.esPrimo(i)) {
                        //System.out.println("El numero " + i + " es primo");
                    }
                }
                System.out.println("Contador " + name + ": " + a);
                barra1.setProgress(barra1.getProgress()+0.01);
                contador++;
            }
            Thread.sleep(1000);
            if (contador==100) {
                ganador.setText("Ganador: " + name);
                pararHilo();
                //interrupt();
            }

        } catch (Exception e) {
            System.out.println(name + " Interrumpido");
        }
    }
    public int getContador() {
        return contador;
    }

}
