/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Main;

import com.company.Interface.VentanaInicio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Alexis
 */
public class SistemaDeTurnos extends Application {

    public static Stage primaryStage;
    public static boolean detener = false;

    @Override
    public void start(Stage arg0) throws Exception {
        primaryStage = arg0;
        Scene sc = new Scene(new VentanaInicio().getRoot(), 830, 450);
        sc.getStylesheets().add("recursos/archivos/CSS/stylesheetPrincipal.css");
        //sc.getStylesheets().addAll(this.getClass().getResource("recursos/archivos/CSS/stylesheetPrincipal.css").toExternalForm());
        primaryStage.setTitle("Sistema de Turnos");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    @Override
    public void stop() {
        detener = true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
