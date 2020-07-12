/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.Interface;

import static com.company.Interface.pruebaVideos.mediaPlayer;
import static com.company.Interface.pruebaVideos.mediaView;
import java.io.File;
import java.util.Iterator;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import recursos.archivos.CircularSimplyLinkedList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Video {
    private static Media media;
    private static MediaPlayer mediaPlayer;
    private static MediaView mediaView;
    private CircularSimplyLinkedList<String> listaVideos = new CircularSimplyLinkedList<>();
    private VBox root;
    
    public Video(){
        
        root = new VBox();
        listaVideos.addFirst("src/recursos/videos/CantFeelMyFace.mp4");
        listaVideos.addFirst("src/recursos/videos/Cheerleader.mp4");
        listaVideos.addFirst("src/recursos/videos/LetHerGo.mp4");
        listaVideos.addFirst("src/recursos/videos/LockedOut.mp4");
        listaVideos.addFirst("src/recursos/videos/Rude.mp4");
        root.getChildren().add(createMediaView(listaVideos));
        
    }
    
    public VBox getRootVideo(){
        return this.root;
    }
    
    private void initMediaPlayer(MediaView media,Iterator<String> iterador){
        if (iterador.hasNext()){
        mediaPlayer = new MediaPlayer(new Media(new File(iterador.next()).toURI().toString()));
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
