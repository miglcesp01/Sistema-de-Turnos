/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alexis
 */
public class LecturaArchivos {
    
    public static CircularSimplyLinkedList<String> leerArchivoVideos(){
        CircularSimplyLinkedList<String> videos=new CircularSimplyLinkedList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader("/src/recursos/archivos/videos.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                videos.addLast(linea);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        return videos;
    }
    
    public static List<String> leerArchivoSintomas(){
        List<String> sintomas=new LinkedList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader("/src/recursos/archivos/sintomas.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                sintomas.add(linea);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        return sintomas;
    }
    
    
}
