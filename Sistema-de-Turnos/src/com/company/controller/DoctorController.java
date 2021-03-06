/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.Interface.VentanaInicio;
import static com.company.Interface.VentanaInicio.actualizarTabla;
import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import com.company.Modelo.Puesto;
import com.company.Modelo.Sistema;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
    private Puesto puesto;

    public DoctorController(Doctor doc, Puesto puesto) {
        operador = doc;
        this.puesto = puesto;
    }

    public void atenderPaciente(Paciente pac) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Label pre = new Label("Bienvenido Paciente " + pac.getNombre() + " ¿Qué sintomas presenta?");
        pre.setStyle("-fx-font-weight:BOLD;-fx-font-size: 16;");
        Label sint = new Label("\n Entiendo, presenta " + pac.getSintoma().getSintoma());
        sint.setStyle("-fx-font-weight:BOLD;-fx-font-size: 16;");
        HBox s=new HBox();
        Label diag = new Label("Según mi experiencia, podría ser:  ");
        diag.setStyle("-fx-font-weight:BOLD;-fx-font-size: 16;");
        Label espa2=new Label("\n");
        TextField txDiag = new TextField();
        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(diag,txDiag);
        Label espa=new Label("\n");
        Label rec = new Label("Le voy a recetar:  ");
        rec.setStyle("-fx-font-weight:BOLD;-fx-font-size: 16;");
        TextField txRec = new TextField();
        HBox r=new HBox();
        r.setAlignment(Pos.CENTER);
        r.getChildren().addAll(rec,txRec);
        Button ok = new Button("ok");
        pac.setReceta(txRec.getText());
        root.setStyle("-fx-background-image: url(recursos/archivos/images/formularios.jpg);");
        root.getChildren().addAll(pre, sint,espa2, s,espa, r, ok);
        Stage window = Action.generarScene(root, "Receta");
        window.show();
        ok.setOnMouseClicked(e -> {
            generarReceta(txRec.getText());
            window.close();
        }); 
        window.setOnCloseRequest(e -> {
            Action.eliminarStage(puesto);
        });
    }

    private void generarReceta(String receta) {
        Label rece = new Label("Necesita comprar: \n" + receta);
        rece.setStyle("-fx-font-size: 24;-fx-text-fill: white;");
        Button ok = new Button("Ok");
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(rece, ok);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-image: url(recursos/archivos/images/imagenReceta.jpg);");
        Stage window = new Stage();
        window.setTitle("Generar Receta");
        window.setMinHeight(500);
        window.setMinWidth(333);
        Scene scene = new Scene((Parent)layout,500,333);
        window.setScene(scene);
        window.show();
        ok.setOnMouseClicked(e -> {
            window.close();
            llamarPaciente();
            Action.eliminarStage(puesto);
        });
    }

    public Doctor getDoctor() {
        return this.operador;
    }

    private void llamarPaciente() {
        if (!Sistema.sistema.getPacientes().isEmpty()) {
            puesto.setPaciente(Sistema.sistema.getPacientes().poll());
        } else {
            puesto.setDisponibilidad(true);
        }
        actualizarTabla();
    }

}
