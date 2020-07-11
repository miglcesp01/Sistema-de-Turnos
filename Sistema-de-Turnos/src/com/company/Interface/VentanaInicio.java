/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Interface;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Alexis
 */
public class VentanaInicio {
    private BorderPane root;
    
    public VentanaInicio(){
        root=new BorderPane();
    }
    
    
    
    public BorderPane getRoot(){
        return root;
    }
    
   
}
