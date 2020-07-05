/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Alexis
 */
public class SistemaDeTurnos extends Application{
    
    @Override
    public void start(Stage arg0) throws Exception {
        Pane root=new Pane();
        root.getChildren().add(new Label("hola mundo"));
        Scene sc = new Scene(root,730,550);
        arg0.setTitle("prueba");
        arg0.setScene(sc);
        arg0.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
