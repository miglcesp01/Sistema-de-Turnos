/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Interface;

import java.io.File;
import java.util.Iterator;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import recursos.archivos.CircularSimplyLinkedList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaVideos {

    private String path = "src/";
    private Media media;
    private MediaView mediaView;
    private VBox v1 = new VBox();
    private VBox root;
    //private CircularSimplyLinkedList<String> listaVideos = new CircularSimplyLinkedList<>();

    public VentanaVideos() {
        //Atributos para la estructura de la ventana
        root = new VBox();
        Label tiempo = new Label("Tiempo");
        Label horario = new Label("Horario Semana");
        HBox h2 = new HBox();
        VBox v2 = new VBox();
        //listaVideos.addFirst("src/v1.mp4");
        //listaVideos.addFirst("src/v2.mp4");
        v2.getChildren().add(tiempo);
        //v2.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().addAll(horario, tiempo);
        //root.setAlignment(Pos.CENTER);
    }

    private MediaView createMediaView(CircularSimplyLinkedList urls) {
        mediaView = new MediaView();
        initMediaPlayer(mediaView, urls.iterator());
        mediaView.setOnMouseClicked(e -> {
            if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
                mediaView.getMediaPlayer().pause();
            } else if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED) {

                mediaView.getMediaPlayer().play();
            }
        });
        mediaView.setFitHeight(225);
        mediaView.setFitWidth(300);
        return mediaView;
    }

    private void initMediaPlayer(MediaView media, Iterator<String> iterador) {
        while (iterador.hasNext()) {
            File f=new File(iterador.next());
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(f.toURI().toString()));
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setOnEndOfMedia(() -> {
                initMediaPlayer(mediaView, iterador);
            });
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    public Parent getRoot() {
        return root;
    }
}
