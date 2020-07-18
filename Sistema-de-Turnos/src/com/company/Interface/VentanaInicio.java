/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Interface;

import com.company.Main.SistemaDeTurnos;
import com.company.Modelo.Sistema;
import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import recursos.archivos.CircularSimplyLinkedList;
import com.company.Interface.Video;
/**
 *
 * @author Alexis
 */
public class VentanaInicio {
    private BorderPane root;
    
    public VentanaInicio() {
        root = new BorderPane();
        crearRigth();
        crearLeft();
        crearBajo();
        
        
        
    }
    
    public BorderPane getRoot() {
        return root;
    }

    private void crearRigth() {
        Label reloj = new Label("");
        Thread cl = new Thread(new Time(reloj));
        cl.start();
        VBox cont=new VBox();
        cont.getChildren().addAll(reloj);
        root.setRight(cont);
    }
    
    private void crearLeft(){
        VBox cont=new VBox();
        Label lo=new Label("Logo");
        cont.getChildren().addAll(lo,(new Video().getVideo()));
        root.setLeft(cont);
    }
    
    private void crearBajo(){
        Label atencion=new Label("Horario de Atención de Lunes a Viernes de 10 a 18 hs/ Sabado");
        root.setBottom(atencion);
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
