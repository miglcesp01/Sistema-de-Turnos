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
import com.company.controller.Formulario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
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
        crearTop();
        crearCentro();
        crearBajo();
        crearLeft();
        crearRight();
        
        
    }
    
    public BorderPane getRoot() {
        return root;
    }

    private void crearTop() {
        Label reloj = new Label("");
        Thread cl = new Thread(new Time(reloj));
        cl.start();
        Label lo=new Label("Logo");
        HBox cont=new HBox();
        cont.getChildren().addAll(lo,reloj);
        cont.setSpacing(600);
        root.setTop(cont);
    }
    
    private void crearLeft(){
        VBox cont=new VBox();
        Button turno=new Button("Sacar un turno");
        Button addDoc=new Button("Ingresar nuevo Doctor");
        Button crearP = new Button("Crear Puesto");
        Button eliminarP = new Button("Eliminar Puesto");
        cont.getChildren().addAll(turno,addDoc,crearP,eliminarP);
        cont.setSpacing(30);
        turno.setOnMouseClicked(e -> {Formulario.crearFormularioPaciente();});
        addDoc.setOnMouseClicked(e -> {Formulario.crearFormularioDoctor();});
        crearP.setOnMouseClicked(e->{
            System.out.println(Sistema.sistema.getPuestos().size());
            Doctor doctor = Sistema.sistema.buscarDoctorDisponible();
            if(doctor!=null)Sistema.sistema.generarPuesto(doctor, Sistema.sistema.getPuestos().size()+1);  
        });
        eliminarP.setOnMouseClicked(e->{
            //Creando la nueva ventana
            Stage window = new Stage();
            window.setTitle("Eliminar Puesto");
            window.setMinHeight(500);
            window.setMinWidth(500);
            //Creando el comboBox de los puestos que existen
            ObservableList<Puesto> puestos= FXCollections.observableList(Sistema.sistema.getPuestos());
            ComboBox comboPuestos=new ComboBox(puestos);
            //Creando el boton para elimianr los puestos
            Button eliminar = new Button("Eliminar Puesto");
            //Creando el contenedor de la venana Eliminar Puesto
            VBox root = new VBox();
            HBox layout = new HBox();
            layout.getChildren().addAll(comboPuestos,eliminar);
            root.getChildren().add(layout);
            Scene scene = new Scene(root,200,200);
            window.setScene(scene);
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
        });
        root.setLeft(cont);
        
    }
    
    private void crearCentro(){
        VBox cont=new VBox();
        cont.getChildren().addAll((new Video().getVideo()));
        root.setCenter(cont);
    }
    
    private void crearRight(){
        tablaTurnos = new TableView();
        TableColumn<Paciente,Puesto> turno=new TableColumn<>("Turno");
        turno.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        TableColumn<Integer,Puesto> puesto=new TableColumn<>("puesto");
        puesto.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tablaTurnos.getColumns().addAll(turno,puesto);
        tablaTurnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        root.setRight(tablaTurnos);
        colocarPuestos();
    }
    
    private void crearBajo(){
        Label atencion=new Label("Horario de Atención de Lunes a Viernes de 10 a 18 hs/ Sabado");
        root.setBottom(atencion);
    }
    
    public static void colocarPuestos(){
        for(Puesto p: Sistema.sistema.getPuestos())
            if(!p.getDisponibilida() && !tablaTurnos.getItems().contains(p)) {
                tablaTurnos.getItems().add(p);
            }
    }
    
    

    private class Time implements Runnable {

        private Label lbl;
        LocalDateTime tp;

        public Time(Label lbl) {
            this.lbl = lbl;
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
