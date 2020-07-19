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
import com.company.controller.DoctorController;
import com.company.controller.Formulario;
import java.util.ArrayList;
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
        //Creando contenedor de los elementos del left
        VBox cont=new VBox();
        
        //Creando los botones de la dinamica del sistema
        Button turno=new Button("Sacar un turno");
        Button addDoc=new Button("Ingresar nuevo Doctor");
        Button crearP = new Button("Crear Puesto");
        Button eliminarP = new Button("Eliminar Puesto");
        Button atenderP = new Button("Atender Paciente");
        
        //Anadiendo los nodos al conteneder del left
        cont.getChildren().addAll(turno,addDoc,crearP,eliminarP,atenderP);
        cont.setSpacing(10);
        
        //Anadiendo las acciones de los botones
        turno.setOnMouseClicked(e -> {Formulario.crearFormularioPaciente();});
        addDoc.setOnMouseClicked(e -> {Formulario.crearFormularioDoctor();});
        crearP.setOnMouseClicked(e->{
            Doctor doctor = Sistema.sistema.buscarDoctorDisponible();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            if(doctor!=null){
                Sistema.sistema.generarPuesto(doctor);
                alert.setHeaderText("Se ha creado un puesto con el doctor: Dr. "+doctor.getNombre());
            }else{
                alert.setHeaderText("No hay doctores disponibles para crear puestos");
            }
            alert.show();
        });
        eliminarP.setOnMouseClicked(e->{
            //Tomando los puestos que estan disponibles
            List<Puesto> puestos = Sistema.sistema.getPuestos();
            List<Puesto> puestos2 = new ArrayList<>();
            for(Puesto p:puestos){
                if(p.getPaciente()==null) puestos2.add(p);
            }
            if(puestos2.size()>0){
                //Creando la nueva ventana
                Stage window = new Stage();
                window.setTitle("Eliminar Puesto");
                window.setMinHeight(500);
                window.setMinWidth(500);
                //Creando el comboBox de los puestos que existen

                ObservableList<Puesto> puestosOL= FXCollections.observableList(puestos2);
                ComboBox comboPuestos=new ComboBox(puestosOL);
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
            }else{
                Stage window = new Stage();
                window.setTitle("Eliminar Puesto");
                window.setMinHeight(300);
                window.setMinWidth(300);
                Label lbl = new Label("No hay puestos disponible");
                Label lbl2 = new Label("Espere a que un paciente sea atendido");
                VBox v1 = new VBox(lbl,lbl2);
                v1.setAlignment(Pos.CENTER);
                Scene scene = new Scene(v1,300,300);
                window.setScene(scene);
                window.show();
            }

        });
        
        atenderP.setOnMouseClicked(e->{
            
            //creando la ventana de atender los pacientes
            Stage window = new Stage();
            window.setTitle("Atender paciente");
            window.setMinHeight(300);
            window.setMinWidth(300);
            //Adquiriendo 
            List<Puesto> puestos = Sistema.sistema.getPuestos();
            List<Puesto> puestos2 = new ArrayList<>();
            for(Puesto p : puestos){
                if(p.getPaciente()!=null) puestos2.add(p);
            }
            ObservableList<Puesto> puestosOL= FXCollections.observableList(puestos2);
            ComboBox comboPuestos=new ComboBox(puestosOL);
            Button atender = new Button("Atender paciente");
            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.getChildren().addAll(comboPuestos,atender);
            atender.setOnMouseClicked(e1->{
               Puesto p = (Puesto)comboPuestos.getValue();
               DoctorController dc = p.getDC();
               
               dc.atenderPaciente(p.getPaciente());
               window.close();
            });
            window.setScene(new Scene(v1,300,300));
            window.show();
            
        });
        
        //Anadiendo el conetenedor al root principal
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
        tablaTurnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaTurnos.getColumns().addAll(turno,puesto);
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
