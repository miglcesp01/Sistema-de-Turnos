/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Interface;

import com.company.Main.SistemaDeTurnos;
import com.company.Modelo.Sistema;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author Alexis
 */
public class VentanaInicio {

    private BorderPane root;

    public VentanaInicio() {
        root = new BorderPane();
        crearTop();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void crearTop() {
        Label reloj = new Label("");
        Thread cl = new Thread(new Time(reloj));
        cl.start();
        root.setTop(reloj);
    }

    private class Time implements Runnable {

        private Label lbl;
        LocalDateTime tp;

        public Time(Label lbl) {
            this.lbl = lbl;
        }

        @Override
        public void run() {

            while (!SistemaDeTurnos.detener) {
                try {
                    tp = LocalDateTime.now();
                    int hour = tp.getHour();
                    int minute = tp.getMinute();
                    int second = tp.getSecond();

                    Platform.runLater(() -> {
                        lbl.setText(hour+":"+minute+":"+second);
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }

}
