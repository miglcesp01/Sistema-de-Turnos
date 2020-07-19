/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.Interface;

import java.io.File;
import java.util.Iterator;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import recursos.archivos.CircularSimplyLinkedList;
import recursos.archivos.LecturaArchivos;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Video {
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    
    public Video(){
        createMediaView(LecturaArchivos.leerArchivoVideos());   
    }
    
    public MediaView getVideo(){
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
    }}
    
        private MediaView createMediaView(CircularSimplyLinkedList urls){
        mediaView = new MediaView();
        initMediaPlayer(mediaView, urls.iterator());
        mediaView.setOnMouseClicked(e->{
            if(mediaView.getMediaPlayer().getStatus()==MediaPlayer.Status.PLAYING){
                mediaView.getMediaPlayer().pause();
            }else if(mediaView.getMediaPlayer().getStatus()==MediaPlayer.Status.PAUSED){
                
                mediaView.getMediaPlayer().play();
            }
        });
        mediaView.setFitHeight(225);
        mediaView.setFitWidth(300);
        return mediaView;
    }
    
    
    

    
}
