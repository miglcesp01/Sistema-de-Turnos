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
    private Puesto puesto;

    public DoctorController(Doctor doc, Puesto puesto) {
        operador = doc;
        this.puesto = puesto;
    }

    public void atenderPaciente(Paciente pac) {
        VBox root = new VBox();
        Label pre = new Label("Bienvenido Paciente " + pac.getNombre() + " ¿Qué sintomas presenta?");
        Label sint = new Label("\n Entiendo, presenta " + pac.getSintoma().getSintoma());
        Label diag = new Label("Según mi experiencia, podría ser:");
        TextField txDiag = new TextField();
        Label rec = new Label("\n Le voy a recetar:");
        TextField txRec = new TextField();
        Button ok = new Button("ok");
        pac.setReceta(txRec.getText());
        root.getChildren().addAll(pre, sint, diag, txDiag, rec, txRec, ok);
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
        Button ok = new Button("Ok");
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(rece, ok);
        layout.setAlignment(Pos.CENTER);
        Stage window = Action.generarScene(layout, "Receta");
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
