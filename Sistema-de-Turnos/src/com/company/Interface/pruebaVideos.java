/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.Interface;

import java.io.File;
import java.util.Iterator;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        
        v2.getChildren().addAll(tiempo);
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
    
    

}
