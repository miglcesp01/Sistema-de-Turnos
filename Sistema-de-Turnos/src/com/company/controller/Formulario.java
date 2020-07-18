/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import com.company.Modelo.Sintoma;
import com.company.Modelo.Sistema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Alexis
 */
public class Formulario {
    
    public static void crearFormularioPaciente(){
        Stage window = new Stage();
        window.setTitle("Sacar turno");
        window.setMinHeight(400);
        window.setMinWidth(400);
        HBox name=txField("Ingrese su nombre: ");
        HBox age=txField("Ingrese su edad: ");
        HBox gender=txField("Igrese su genero(M o F): " );
        ObservableList<Sintoma> sintomas= FXCollections.observableList(Sistema.sistema.getSintomas());
        ComboBox sint=new ComboBox(sintomas);
        HBox syntomy=new HBox(new Label("ELija su sintoma"),sint);
        Button ok = new Button("Ok");
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name,age,gender,syntomy,ok);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,200,200);
        window.setScene(scene);
        window.show();
        ok.setOnMouseClicked(e -> { 
            String n=obtenerTexto(name);
            String a=obtenerTexto(age);
            String g=obtenerTexto(gender);
            if(n!=null && a!=null && g!=null){
                Sistema.sistema.agregarPaciente(new Paciente(n,Integer.parseInt(a),g.charAt(0),(Sintoma)sint.getValue()));
                window.close();
            }else layout.getChildren().add(new Label("Ingreso algun valor erroneo")); 
             });
    }
    
    public static void crearFormularioDoctor(){
        Stage window = new Stage();
        window.setTitle("Ingreso de nuevo Doctor");
        window.setMinHeight(400);
        window.setMinWidth(400);
        HBox name=txField("Ingrese su nombre: ");
        HBox esp=txField("Ingrese su especialidad: ");
        HBox id=txField("Igrese su identificación: " );
        Button ok = new Button("Ok");
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name,esp,id,ok);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,200,200);
        window.setScene(scene);
        window.show();
        ok.setOnMouseClicked(e -> { 
            String n=obtenerTexto(name);
            String es=obtenerTexto(esp);
            String i=obtenerTexto(id);
            if(n!=null && es!=null && i!=null){
                Sistema.sistema.agregarDoctor(new Doctor(n,es,i));
                window.close();
            }else layout.getChildren().add(new Label("Ingreso algun valor erroneo")); 
             });
    }
    
    
    
    private static HBox txField(String texto){
        HBox contG=new HBox();
        Label lbl=new Label(texto);
        TextField field=new TextField();
        contG.getChildren().addAll(lbl,field);
        return contG;
    }
    
    private static String obtenerTexto(HBox box){
        return ((TextField)box.getChildren().get(1)).getText();
    }
    
}