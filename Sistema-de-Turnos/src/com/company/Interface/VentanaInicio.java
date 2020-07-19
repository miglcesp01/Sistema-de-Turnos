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
import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import com.company.Modelo.Puesto;
import com.company.Modelo.Puesto;
import com.company.Modelo.Sintoma;
import com.company.controller.Action;
import com.company.controller.DoctorController;
import com.company.controller.Formulario;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author Alexis
 */
public class VentanaInicio {
    
    private BorderPane root;
    private static TableView tablaTurnos;
    
    public VentanaInicio() {
        root = new BorderPane();
        root.setId("bpane");
        crearTop();
        crearCentro();
        crearBajo();
        crearLeft();
        crearRight();
        root.setStyle("-fx-background-image: url(recursos/archivos/images/VentanaInicio.jpg);"
                + "-fx-background-repeat: stretch;");
    }
    
    public BorderPane getRoot() {
        return root;
    }

    private void crearTop() {
        Image img = new Image("recursos/archivos/images/Logo (1).png");
        ImageView imgv = new ImageView(img);
        Label reloj = new Label("");
        Thread cl = new Thread(new Time(reloj));
        cl.start();
        
        VBox imagen = new VBox();
        VBox hora = new VBox();
        imagen.getChildren().add(imgv);
        hora.getChildren().add(reloj);
        imagen.setAlignment(Pos.CENTER);
        hora.setAlignment(Pos.TOP_RIGHT);
        
        HBox cont=new HBox();
        cont.getChildren().addAll(imagen, hora);
        cont.setSpacing(600);
        root.setTop(cont);
    }
    
    private void crearLeft(){
        //Creando contenedor de los elementos del left
        VBox cont=new VBox();
        
        //Creando los botones de la dinamica del sistema
        Button turno=new Button("Sacar un turno");
        Button addDoc=new Button("Ingresar nuevo Doctor");
        Button crearP = new Button("Crear Puesto");
        Button eliminarP = new Button("Eliminar Puesto");
        Button atenderP = new Button("Atender Paciente");
        turno.setId("botonesVI");
        addDoc.setId("botonesVI");
        eliminarP.setId("botonesVI");
        atenderP.setId("botonesVI");
        crearP.setId("botonesVI");
        //Anadiendo los nodos al conteneder del left
        cont.getChildren().addAll(turno,addDoc,crearP,eliminarP,atenderP);
        cont.setSpacing(10);
        
        //Anadiendo las acciones de los botones
        turno.setOnMouseClicked(e -> {Formulario.crearFormularioPaciente();});
        addDoc.setOnMouseClicked(e -> {Formulario.crearFormularioDoctor();});
        crearP.setOnMouseClicked(e->{ Action.crearPuesto();});
        eliminarP.setOnMouseClicked(e->{ Action.eliminarPuesto(); });
        
        atenderP.setOnMouseClicked(e->{ Action.atenderPaciente(); });
        //Anadiendo el conetenedor al root principal
        root.setLeft(cont);
    
    }
    
    private void crearCentro(){
        VBox cont=new VBox();
        cont.setId("video");
        cont.setAlignment(Pos.CENTER);
        cont.getChildren().addAll((new Video().getVideo()));
        root.setCenter(cont);
        
    }
    
    private void crearRight(){
        tablaTurnos = new TableView();
        tablaTurnos.setId("tabla");
        TableColumn<Paciente,Puesto> turno=new TableColumn<>("Turno");
        turno.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        TableColumn<Integer,Puesto> puesto=new TableColumn<>("puesto");
        puesto.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tablaTurnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaTurnos.getColumns().addAll(turno,puesto);
        tablaTurnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        root.setRight(tablaTurnos);
        colocarPuestos();
    }
    
    private void crearBajo(){
        Label atencion=new Label("Horario de Atención de Lunes a Viernes de 10 a 18 hrs/ Sábado");
        atencion.setStyle("-fx-text-fill:white;"
                + "-fx-font-size:20px");
        root.setAlignment(atencion, Pos.CENTER);
        root.setBottom(atencion);
    }
    
    public static void colocarPuestos(){
        for(Puesto p: Sistema.sistema.getPuestos()){
            System.out.println(tablaTurnos.getItems().contains(p));
            if(!p.isDisponible() && (!tablaTurnos.getItems().contains(p))) {
                tablaTurnos.getItems().add(p);
            }
        }
    }
    
    public static void actualizarTabla(){
        tablaTurnos.getItems().clear();
        colocarPuestos();
    }
    

    private class Time implements Runnable {

        private Label lbl;
        LocalDateTime tp;

        public Time(Label lbl) {
            this.lbl = lbl;
            lbl.setId("hora");
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
