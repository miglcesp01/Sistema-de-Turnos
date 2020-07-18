/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.Interface;

import com.company.Modelo.Paciente;
import com.company.Modelo.Puesto;
import com.company.Modelo.Sintoma;
import com.company.Modelo.Sistema;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import recursos.archivos.CircularSimplyLinkedList;
import recursos.archivos.LecturaArchivos;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class pruebaVideos extends Application {
    public static String path = "src/";
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static MediaView mediaView;
    public static VBox v1;
    private CircularSimplyLinkedList<String> listaVideos;
    
    public void start(Stage primaryStage) {
        HBox h2 = new HBox();
        v1 = new VBox();
        VBox v2 = new VBox();
        listaVideos=LecturaArchivos.leerArchivoVideos();
        
        Label tiempo = new Label("Tiempo");
        Label horario = new Label("Horario atencion");
        Label logo = new Label("Logo");
        
        v1.setStyle("-fx-border-color:blue");
        v2.setStyle("-fx-border-color:red");
        v1.setPrefSize(225,300);
        v2.setPrefSize(225,300);
        
       /*
        media = new Media(new File(path).toURI().toString());
        
        mediaPlayer = new MediaPlayer(media); 
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
        });
        
        System.out.println(media.getDuration().toMinutes());
        mediaView = new MediaView (mediaPlayer);
        */
        
        
        v1.getChildren().addAll(logo,createMediaView(listaVideos));
        TableView tabla = generarTabla();
        Button botonPrueba = new Button("Prueba eliminar");
        Button botonAgregar = new Button("Prueba agregar");
        v2.getChildren().addAll(tiempo,tabla,botonPrueba,botonAgregar);
        //Puesto Prueba para eliminacion; ignorar
        Puesto p1 = new Puesto(Sistema.sistema.getDoctores().get(1),1);
        p1.setPaciente(new Paciente("Eddo",12,'m',4,new Sintoma("Gripe",1)));
        botonPrueba.setOnAction(e->{
            tabla.getItems().remove(p1);
        });
        botonAgregar.setOnAction(e->{
             tabla.getItems().add(p1);
        });
        v2.setAlignment(Pos.TOP_RIGHT);
        h2.getChildren().addAll(v1,v2);
        VBox root = new VBox(h2,horario);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root,450,300);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }
    
    private MediaView createMediaView(CircularSimplyLinkedList urls){
        mediaView = new MediaView();
        initMediaPlayer(mediaView, urls.iterator());
        mediaView.setOnMouseClicked(e->{
            if(mediaView.getMediaPlayer().getStatus()==Status.PLAYING){
                mediaView.getMediaPlayer().pause();
            }else if(mediaView.getMediaPlayer().getStatus()==Status.PAUSED){
                
                mediaView.getMediaPlayer().play();
            }
        });
        mediaView.setFitHeight(225);
        mediaView.setFitWidth(300);
        return mediaView;
    }
    
    private void initMediaPlayer(MediaView media,Iterator<String> iterador){
        if (iterador.hasNext()){
        mediaPlayer = new MediaPlayer(new Media(new File(("src/recursos/videos/"+iterador.next())).toURI().toString()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            initMediaPlayer(mediaView, iterador);
        });
        mediaView.setMediaPlayer(mediaPlayer);
    } 
    }
    
    
    private TableView generarTabla(){
        TableView tabla=new TableView();
        tabla.setEditable(true);
        List<Paciente> to=new LinkedList<>();
        to.add(new Paciente("Eddo",12,'m',Sistema.sistema.generarTurno(),new Sintoma("Gripe",1)));
        to.add(new Paciente("Eddo",13,'m',Sistema.sistema.generarTurno(),new Sintoma("Gripe",1)));
        to.add(new Paciente("Eddo",14,'m',Sistema.sistema.generarTurno(),new Sintoma("Gripe",1)));
        List<Puesto> p=new LinkedList<>();
        Puesto p1 = new Puesto(Sistema.sistema.getDoctores().get(1),1);
        p1.setPaciente(new Paciente("Eddo",12,'m',Sistema.sistema.generarTurno(),new Sintoma("Gripe",1)));
        /*
        p.add(new Puesto(Sistema.sistema.getDoctores().get(1),1));
        p.add(new Puesto(Sistema.sistema.getDoctores().get(2),2));
        p.add(new Puesto(Sistema.sistema.getDoctores().get(3),3));
        */
        p.add(p1);
        TableColumn<Paciente,Puesto> turno=new TableColumn<>("Turno");
        turno.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        TableColumn<Integer,Puesto> puesto=new TableColumn<>("puesto");
        puesto.setCellValueFactory(new PropertyValueFactory<>("numero"));
        
        tabla.getColumns().addAll(turno,puesto);
        tabla.getItems().addAll(p);
        
        return tabla;
    }
    

}
