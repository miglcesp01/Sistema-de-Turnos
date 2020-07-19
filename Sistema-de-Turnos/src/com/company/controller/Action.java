/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.Interface.VentanaInicio;
import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import com.company.Modelo.Puesto;
import com.company.Modelo.Sistema;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Alexis
 */
public class Action {
    private static List<Puesto> puestosEnAtencion = new LinkedList<>();

    public static void crearPuesto() {
        Doctor doctor = Sistema.sistema.buscarDoctorDisponible();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        if (doctor != null) {
            Sistema.sistema.generarPuesto(doctor);
            alert.setHeaderText("Se ha creado un puesto con el doctor: Dr. " + doctor.getNombre());
        } else {
            alert.setHeaderText("No hay doctores disponibles para crear puestos");
        }
        alert.show();

    }
    
    public static void eliminarPuesto(){
        //Tomando los puestos que estan disponibles
            List<Puesto> pustDisp=Sistema.sistema.puestosDisponible();
            if(!pustDisp.isEmpty()){
                //Creando la nueva ventana
                //Creando el comboBox de los puestos que existen
                ObservableList<Puesto> puestosOL= FXCollections.observableList(pustDisp);
                ComboBox comboPuestos=new ComboBox(puestosOL);
                //Creando el boton para elimianr los puestos
                Button eliminar = new Button("Eliminar Puesto");
                //Creando el contenedor de la venana Eliminar Puesto
                VBox root = new VBox();
                HBox layout = new HBox();
                root.setAlignment(Pos.CENTER);
                layout.setAlignment(Pos.CENTER);
                layout.getChildren().addAll(comboPuestos,eliminar);
                root.getChildren().add(layout);
                root.setStyle("-fx-background-image:url(recursos/archivos/images/eliminarP.jpg)");
                Stage window=generarScene(root,"Eliminar Puesto");
                window.show();
                //Accion del elemento eliminar
                eliminar.setOnMouseClicked(e1->{
                    Puesto p = (Puesto) comboPuestos.getValue();
                    if(p!=null){
                        Sistema.sistema.eliminarPuesto(p);
                        window.close();  
                    }
                    else{
                        Label lbl = new Label("No se ha escogido un Puesto");
                        HBox H2 = new HBox(lbl);
                        H2.setAlignment(Pos.CENTER);
                        root.getChildren().add(H2);
                    }
                });
            }else{ 
                Label lbl = new Label("No hay puestos disponible");
                Label lbl2 = new Label("Espere a que un paciente sea atendido");
                lbl.setStyle("-fx-text-fill:black;-fx-font-size:25");
                lbl2.setStyle("-fx-text-fill:black;-fx-font-size:25");
                VBox v1 = new VBox(lbl,lbl2);
                v1.setStyle("-fx-background-image:url(recursos/archivos/images/eliminarP.jpg)");
                v1.setAlignment(Pos.CENTER);
                Stage window =generarScene(v1, "Eliminar Puesto");
                window.show();
            }

        
    }
    
    public static Stage generarScene(Node root,String titulo){
        Stage window = new Stage();
        window.setTitle(titulo);
        window.setMinHeight(500);
        window.setMinWidth(500);
        Scene scene = new Scene((Parent)root,200,200);
        window.setScene(scene);
        return window;
    }
    
    public static void atenderPaciente(){
            //Adquiriendo 
            List<Puesto> puestos = Sistema.sistema.puestosOcupados();
            if(puestos.size()>0){
                ObservableList<Puesto> puestosOL= FXCollections.observableList(puestos);
                ComboBox comboPuestos=new ComboBox(puestosOL);
                Button atender = new Button("Atender paciente");
                VBox v1 = new VBox();
                v1.setAlignment(Pos.CENTER);
                v1.getChildren().addAll(comboPuestos,atender);
                v1.setStyle("-fx-background-image:url(recursos/archivos/images/eliminarP.jpg)");
                Stage window=generarScene(v1, "Atender Paciente");
                window.show();
                atender.setOnMouseClicked(e1->{
                   Puesto p = (Puesto)comboPuestos.getValue();
                    System.out.println(p);
                   if(p!=null && !puestosEnAtencion.contains(p)){
                       p.atencion();
                       puestosEnAtencion.add(p);
                   }

                   window.close();
                });
            }else{
                Label lbl = new Label("No hay pacientes para atender");
                Label lbl2 = new Label("Espere a que un paciente ingrese");
                lbl.setStyle("-fx-text-fill:black;-fx-font-size:25");
                lbl2.setStyle("-fx-text-fill:black;-fx-font-size:25");
                VBox v1 = new VBox(lbl,lbl2);
                v1.setStyle("-fx-background-image:url(recursos/archivos/images/eliminarP.jpg)");
                v1.setAlignment(Pos.CENTER);
                Stage window =generarScene(v1, "Atender Paciente");
                window.show();
            }
            
            
    }
    
    public static void eliminarStage(Puesto puesto){
       puestosEnAtencion.remove(puesto);
        VentanaInicio.actualizarTabla();
    } 
}
