package com.example.ejerciciohebra;

import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.concurrent.Executors;

public class HelloController {

    @FXML
    private Slider slider1;
    @FXML
    private Slider slider2;
    @FXML
    private Slider slider3;
    @FXML
    private Text txtSlider1;
    @FXML
    private Text txtSlider2;
    @FXML
    private Text txtSlider3;

    private CalculaPrimos cp1, cp2, cp3;

    @FXML
    private Button boton;

    @FXML
    private ProgressBar progressBar1;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private ProgressBar progressBar3;

    @FXML
    private Label tvResultado;

    //Incremento de 1 en 1
    final ChangeListener<Number> numberChangeListener = (obs, old, val) -> {
        final double roundedValue = Math.floor(val.doubleValue() / 1.0) * 1.0;
        slider1.valueProperty().set(roundedValue);
        txtSlider1.setText("Prioridad: " + (roundedValue));
    };

    final ChangeListener<Number> numberChangeListener2 = (obs, old, val) -> {
        final double roundedValue = Math.floor(val.doubleValue() / 1.0) * 1.0;
        slider2.valueProperty().set(roundedValue);
        txtSlider2.setText("Prioridad: " + (roundedValue));
    };

    final ChangeListener<Number> numberChangeListener3 = (obs, old, val) -> {
        final double roundedValue = Math.floor(val.doubleValue() / 1.0) * 1.0;
        slider3.valueProperty().set(roundedValue);
        txtSlider3.setText("Prioridad: " + (roundedValue));
    };

    final ChangeListener<Label> resultadoChangeListener = (observableValue, text, t1) -> {
        tvResultado.setText(String.valueOf(progressBar1.getProgress()));
    };
    @FXML
    private Label tvHilo1;
    @FXML
    private Label tvHilo2;
    @FXML
    private Label tvHilo3;


    @FXML
    public void metodoArrastrar(Event event) {
        slider1.valueProperty().addListener(numberChangeListener);
        cp1.setPriority((int) slider1.getValue());
    }

    @FXML
    public void metodoArrastrar2(Event event) {
        slider2.valueProperty().addListener(numberChangeListener2);
        cp2.setPriority((int) slider2.getValue());

    }

    @FXML
    public void metodoArrastrar3(Event event) {
        slider3.valueProperty().addListener(numberChangeListener3);
        cp3.setPriority((int) slider3.getValue());
    }

    public void initialize() {
        txtSlider1.setText("Prioridad: " + (slider1.getValue()));
        txtSlider2.setText("Prioridad: " + (slider2.getValue()));
        txtSlider3.setText("Prioridad: " + (slider3.getValue()));

    }

    public void ganador() {
        tvResultado.setText(String.valueOf(progressBar1.getProgress()));

    }

    @FXML
    public void presionarBoton(Event event) {
        cp1 = new CalculaPrimos(1, 50000, progressBar1, tvResultado, "Hilo 1", tvHilo1);
        cp2 = new CalculaPrimos(1, 50000, progressBar2, tvResultado, "Hilo 2", tvHilo2);
        cp3 = new CalculaPrimos(1, 50000, progressBar3, tvResultado, "Hilo 3", tvHilo3);

        cp1.start();
        cp2.start();
        cp3.start();

        if (progressBar1.getProgress() == 1.0 && progressBar2.getProgress() < 1.0 && progressBar3.getProgress() < 1.0) {
            tvResultado.setText("Hilo 1");
        }
        if (progressBar1.getProgress() < 1.0 && progressBar2.getProgress() == 1.0 && progressBar3.getProgress() < 1.0) {
            tvResultado.setText("Hilo 2");
        }
        if (progressBar1.getProgress() < 1.0 && progressBar2.getProgress() < 1.0 && progressBar3.getProgress() == 1.0) {
            tvResultado.setText("Hilo 3");
        }

    }
}