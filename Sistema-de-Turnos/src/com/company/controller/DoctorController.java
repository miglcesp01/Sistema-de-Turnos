/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Alexis
 */
public class DoctorController {
    private Doctor operador;
    
    public DoctorController(Doctor doc){
        operador=doc;
    }
    
    public Pane atenderPaciente(Paciente pac){
        VBox root=new VBox();
        Label pre=new Label("Bienvenido Paciente "+pac.getNombre()+" ¿Qué sintomas presenta?");
        Label sint=new Label("\n Entiendo, presenta "+pac.getSintoma().getSintoma());
        Label diag=new Label("Según mi experiencia, podría ser:");
        TextField txDiag=new TextField();
        Label rec=new Label("\n Le voy a recetar:");
        TextField txRec=new TextField();
        
        return root;
    }
    
    private void generarReceta(String receta){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Receta");
        window.setMinHeight(200);
        window.setMinWidth(400);
        Label rece=new Label("Necesita comprar: \n"+receta);
        Button ok = new Button("Ok");
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(rece,ok);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,200,200);
        window.setScene(scene);
        window.show();
        ok.setOnMouseClicked(e -> { window.close(); });
        
    }
    
}
