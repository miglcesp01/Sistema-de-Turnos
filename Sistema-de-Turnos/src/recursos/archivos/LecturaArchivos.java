/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.archivos;

import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import com.company.Modelo.Sintoma;
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
        try(BufferedReader bf=new BufferedReader(new FileReader("src/recursos/archivos/videos.txt"))){
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
    
    public static List<Sintoma> leerArchivoSintomas(){
        List<Sintoma> sintomas=new LinkedList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader("/src/recursos/archivos/sintomas.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] lista=linea.split("|");
                sintomas.add(new Sintoma(lista[0],Integer.parseInt(lista[1])));
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        return sintomas;
    }
    
    public static List<Doctor> leerArchivoDoctor(){
        List<Doctor> doctores=new LinkedList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader("/src/recursos/archivos/doctores.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] lista=linea.split(",");
                doctores.add(new Doctor(lista[0],lista[1],lista[2]));
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }return doctores;
    }
    
    public static List<Paciente> leerArchivoPaciente(){
        List<Paciente> pacientes=new LinkedList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader("/src/recursos/archivos/pacientes.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] lista=linea.split(",");
                pacientes.add(new Paciente(lista[0],Integer.parseInt(lista[1]),lista[2].charAt(0),new Sintoma(lista[3],Integer.parseInt(lista[4]))));
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }return pacientes;
    }
    
}
