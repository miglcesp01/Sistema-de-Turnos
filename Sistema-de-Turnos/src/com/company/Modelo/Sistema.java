package com.company.Modelo;

import com.company.Interface.VentanaInicio;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import recursos.archivos.LecturaArchivos;

public class Sistema {
    public static Sistema sistema=new Sistema();
    private List<Doctor> doctores;
    private PriorityQueue<Paciente> pacientes;
    private List<Puesto> puestos;
    private List<Sintoma> sintomas;
    private int turnos=1;
    private int numeroPuestos = 1;

    private Sistema() {
        //this.doctores = LecturaArchivos.leerArchivoDoctor();
        this.doctores = new LinkedList<>();
        this.pacientes = LecturaArchivos.leerArchivoPaciente();
        //this.pacientes = new PriorityQueue<>((Paciente p1, Paciente p2)-> p1.getSintoma().getPrioridad()-p2.getSintoma().getPrioridad());
        this.puestos = new LinkedList<>();
        this.sintomas=LecturaArchivos.leerArchivoSintomas();
        asignarTurnosIniciales();
    }
    
    private void asignarTurnosIniciales(){
        List<Paciente> tmp = new LinkedList<>(); 
        while(!pacientes.isEmpty()){
            Paciente p = pacientes.poll();
            p.setTurno(turnos++);
            tmp.add(p);
        }
        pacientes.addAll(tmp);
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }
    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }
    public PriorityQueue<Paciente> getPacientes() {
        return pacientes;
    }
    public void setPacientes(PriorityQueue<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    public List<Puesto> getPuestos() {
        return puestos;
    }
    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public List<Sintoma> getSintomas(){
        return sintomas;
    }
    
    public boolean agregarDoctor(Doctor doctor){
        if(doctores.contains(doctor) || doctor==null) return false;
        doctores.add(doctor);
        return true;
    }
    
    public boolean generarPuesto(Doctor doctor){
        Puesto p = new Puesto(doctor,numeroPuestos++);
        puestos.add(p);
        doctor.setDisponibilidad(false);
        if(!pacientes.isEmpty()){
            p.setPaciente(pacientes.poll());
            VentanaInicio.colocarPuestos();
        }
        return true;
    }
    
    public void agregarPaciente(Paciente p){
        if(p==null) throw new NullPointerException("Ingreso un paciente vacio");
        pacientes.offer(p);
        p.setTurno(generarTurno());
        Puesto puesto = buscarPuestoDisponible();
        if(puesto!=null){
            puesto.setPaciente(pacientes.poll());
            VentanaInicio.colocarPuestos();
        }
        
    }
    public boolean eliminarPuesto(Puesto p){
        if(p!=null || puestos.contains(p)){
            p.getDoctor().setDisponibilidad(true);
            puestos.remove(p);
        } 
        return false;
    }

    public int generarTurno(){
        return turnos++;
    }
    
    public Doctor buscarDoctorDisponible(){
        for(Doctor d: doctores){
            if(d.getDisponibilidad()) return d;
        }return null;
    }
    
    public Puesto buscarPuestoDisponible(){
        for(Puesto p: puestos){
            if(p.isDisponible()) return p;
        }return null;
    }
    
    public List<Puesto> puestosDisponible(){
        List<Puesto> disponibles=new ArrayList<>();
        for(Puesto p: puestos)
            if(p.isDisponible()) disponibles.add(p);
        return disponibles;
    }
    
    public List<Puesto> puestosOcupados(){
        List<Puesto> disponibles=new ArrayList<>();
        for(Puesto p: puestos)
            if(!p.isDisponible()) disponibles.add(p);
        return disponibles;
        }
}
