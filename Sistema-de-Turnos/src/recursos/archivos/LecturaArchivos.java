/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.archivos;

import com.company.Modelo.Doctor;
import com.company.Modelo.Paciente;
import com.company.Modelo.Sintoma;
import com.company.Modelo.Sistema;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
        try(BufferedReader bf=new BufferedReader(new FileReader("src/recursos/archivos/sintomas.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] lista=linea.split("\\|");
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
        try(BufferedReader bf=new BufferedReader(new FileReader("src/recursos/archivos/doctores.txt"))){
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
    
    public static PriorityQueue<Paciente> leerArchivoPaciente(){
        PriorityQueue<Paciente> pacientes=new PriorityQueue<>((Paciente p1, Paciente p2)-> p1.getSintoma().getPrioridad()-p2.getSintoma().getPrioridad());
        try(BufferedReader bf=new BufferedReader(new FileReader("src/recursos/archivos/pacientes.txt"))){
            String linea;
            Paciente p;
            while((linea=bf.readLine())!=null){
                String[] lista=linea.split(",");
                p=new Paciente(lista[0],Integer.parseInt(lista[1]),lista[2].charAt(0),Integer.parseInt(lista[3]),new Sintoma(lista[4],(int)Integer.parseInt(lista[5])));
                pacientes.offer(p);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }return pacientes;
    }
    
}
