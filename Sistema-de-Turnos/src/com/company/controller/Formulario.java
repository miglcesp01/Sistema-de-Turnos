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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Alexis
 */
public class Formulario {
    
    public static void crearFormularioPaciente(){
        HBox name=txField("Ingrese su nombre: ");
        HBox age=txField("Ingrese su edad: ");
        HBox gender=txField("Igrese su genero(M o F): " );
        ObservableList<Sintoma> sintomas= FXCollections.observableList(Sistema.sistema.getSintomas());
        ComboBox sint=new ComboBox(sintomas);
        Region espacio = new Region();
        HBox syntomy=new HBox(new Label("ELija su sintoma"),espacio,sint);
        HBox.setHgrow(espacio, Priority.ALWAYS);
        Button ok = new Button("Ok");
        Label error = new Label("Ingreso algun valor erroneo");
        error.setVisible(false);
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name,age,gender,syntomy,ok,error);
        layout.setAlignment(Pos.CENTER);
        Stage window=generarScene(layout, "Sacar Turno");
        window.show();
        ok.setOnMouseClicked(e -> { 
            String n=obtenerTexto(name);
            String a=obtenerTexto(age);
            String g=obtenerTexto(gender);
            if(!"".equals(n) && !"".equals(a) && !"".equals(g)){
                Paciente p=new Paciente(n,Integer.parseInt(a),g.charAt(0),(Sintoma)sint.getValue());
                Sistema.sistema.agregarPaciente(p);
                window.close();
            }else error.setVisible(true); 
             });
    }
    
    public static void crearFormularioDoctor(){
        HBox name=txField("Ingrese su nombre: ");
        HBox esp=txField("Ingrese su especialidad: ");
        HBox id=txField("Igrese su identificación: " );
        Button ok = new Button("Ok");
        VBox layout=new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name,esp,id,ok);
        layout.setAlignment(Pos.CENTER);
        Stage window = generarScene(layout, "Crear Doctor");
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
    
    private static Stage generarScene(Node root,String titulo){
        Stage window = new Stage();
        window.setTitle(titulo);
        window.setMinHeight(400);
        window.setMinWidth(400);
        Scene scene = new Scene((Parent)root,200,200);
        window.setScene(scene);
        return window;
    }
    
    
    private static HBox txField(String texto){
        HBox contG=new HBox();
        Label lbl=new Label(texto);
        TextField field=new TextField();
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);
        contG.getChildren().addAll(lbl,espacio,field);
        return contG;
    }
    
    private static String obtenerTexto(HBox box){
        return ((TextField)box.getChildren().get(2)).getText();
    }
    
}
