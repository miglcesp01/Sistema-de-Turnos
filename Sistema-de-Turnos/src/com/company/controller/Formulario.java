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
import javafx.scene.control.Alert;
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

    public static void crearFormularioPaciente() {
        HBox name = txField("Ingrese su nombre: ");
        HBox age = txField("Ingrese su edad: ");
        HBox gender = txField("Igrese su genero(M o F): ");
        ObservableList<Sintoma> sintomas = FXCollections.observableList(Sistema.sistema.getSintomas());
        ComboBox sint = new ComboBox(sintomas);
        sint.getSelectionModel().selectFirst();
        Region espacio = new Region();
        HBox syntomy = new HBox(new Label("ELija su sintoma"), espacio, sint);
        HBox.setHgrow(espacio, Priority.ALWAYS);
        Button ok = new Button("Ok");
        Label error = new Label("Ingreso algun valor erroneo");
        error.setVisible(false);
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name, age, gender, syntomy, ok, error);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-image: url(recursos/archivos/images/formularios.jpg);");
        Stage window = Action.generarScene(layout, "Sacar Turno");
        window.show();
        ok.setOnMouseClicked(e -> {
            String n = obtenerTexto(name);
            String a = obtenerTexto(age);
            String g = obtenerTexto(gender);
            if (!"".equals(n) && !"".equals(a) && !"".equals(g) && (Sintoma) sint.getValue() != null) {
                try {
                    Paciente p = new Paciente(n, Integer.parseInt(a), g.charAt(0), (Sintoma) sint.getValue());
                    Sistema.sistema.agregarPaciente(p);
                    window.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Se ha generado su turno de atención: " + p.getTurno());
                    alert.show();
                } catch (NumberFormatException ex) {
                    error.setVisible(true);
                }

            } else {
                error.setVisible(true);
            }
        }
        );
    }

    public static void crearFormularioDoctor() {
        HBox name = txField("Ingrese su nombre: ");
        HBox esp = txField("Ingrese su especialidad: ");
        HBox id = txField("Igrese su identificación: ");
        Button ok = new Button("Crear Doctor");
        Label error = new Label("Ingreso algun valor erroneo");
        error.setVisible(false);
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(5);
        layout.getChildren().addAll(name, esp, id, ok,error);
        layout.setStyle("-fx-background-image: url(recursos/archivos/images/formularios.jpg);");
        Stage window = Action.generarScene(layout, "Crear Doctor");
        window.show();
        ok.setOnMouseClicked(e -> {
            String n = obtenerTexto(name);
            String es = obtenerTexto(esp);
            String i = obtenerTexto(id);
            if (!"".equals(n) && !"".equals(es) && !"".equals(i)) {
                Sistema.sistema.agregarDoctor(new Doctor(n, es, i));
                window.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Ha sido ingresado al sistema del hospital: DR." + n);
                alert.show();
            } else {
                error.setVisible(true);
            }
        });
    }

    private static HBox txField(String texto) {
        HBox contG = new HBox();
        Label lbl = new Label(texto);
        TextField field = new TextField();
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);
        contG.getChildren().addAll(lbl, espacio, field);
        return contG;
    }

    private static String obtenerTexto(HBox box) {
        return ((TextField) box.getChildren().get(2)).getText();
    }

}
